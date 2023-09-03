import { useContext, useState } from "react";
import { Alert, Button, Form, Table } from "react-bootstrap";
import cookie from "react-cookies";
import { MyCartContext, MyUserContext } from "../App";
import { Link } from "react-router-dom";
import { authApi, endpoints } from "../configs/Apis";

const Cart = () => {
    const [user,] = useContext(MyUserContext);
    const [, cartDispatch] = useContext(MyCartContext);
    const [carts, setCarts] = useState(cookie.load("cart") || null);
    console.log(carts)

    const deleteTour = (item) => {
        cartDispatch({
            "type": "dec",
            "payload": item.adultQuantity + item.childQuantity

        });

        if (item.id in carts) {
            setCarts(current => {
                delete current[item.id];
                cookie.save("cart", current);

                return current;
            });
        }
    }


const updateTour = () => {
    cookie.save("carts", carts);
    let sum = Object.values(carts).reduce((init, current) => init + current["adultQuantity"] + current["childQuantity"], 0)
    cartDispatch({
        "type": "update",
        "payload": sum
    });
}

const pay = () => {
    const process = async () => {
        let res = await authApi().post(endpoints['pay'], carts);
        if (res.status === 200) {
            cookie.remove("cart");

            cartDispatch({
                "type": "update",
                "payload": 0
            });

            setCarts([]);
        }
    }

    process();
}

if (carts === null)
    return <Alert variant="info" className="mt-2">Không có tour cần thanh toán!!!</Alert>

if (carts.length === 0)
    return <Alert variant="success" className="mt-2">Thanh toán thành công!</Alert>

return (
    <>
        <h1 className="text-center text-info">GIỎ HÀNG</h1>
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Tên tour</th>
                    <th>Giá người lớn</th>
                    <th>Giá trẻ em</th>
                    <th>Số lượng người lớn</th>
                    <th>Số lượng trẻ em</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {Object.values(carts).map(c => {
                    return (
                        <tr>
                            <td>{c.id}</td>
                            <td>{c.name}</td>
                            <td>{c.adultUnitPrice} VNĐ</td>
                            <td>{c.childUnitPrice} VNĐ</td>
                            <td>
                                <Form.Control type="number" onBlur={updateTour} value={carts[c.id]["adultQuantity"]}
                                    onChange={e => setCarts({ ...carts, [c.id]: { ...carts[c.id], "adultQuantity": parseInt(e.target.value) } })} />
                            </td>
                            <td>
                                <Form.Control type="number" onBlur={updateTour} value={carts[c.id]["childQuantity"]}
                                    onChange={e => setCarts({ ...carts, [c.id]: { ...carts[c.id], "childQuantity": parseInt(e.target.value) } })} />
                            </td>
                            <td>
                                <Button variant="danger" onClick={() => deleteTour(c)}>&times;</Button>
                            </td>
                        </tr>
                    )
                })}

            </tbody>
        </Table>
        {user === null ? <p>Vui lòng <Link to="/login?next=/cart">đăng nhập</Link> để thanh toán</p> : <Button onClick={pay} variant="info mt-2 mb-2">Thanh toán</Button>}

    </>
)



}

export default Cart;