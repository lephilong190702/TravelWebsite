import { useContext, useRef, useState } from "react";
import { Button, Form } from "react-bootstrap";
import Apis, { authApi, endpoints } from "../configs/Apis";
import cookie from "react-cookies";
import { MyUserContext } from "../App";
import { Navigate, useNavigate } from "react-router-dom";
import MySpinner from "../layout/MySpinner";


const Register = () => {
    const [user, setUser] = useState({
        "userUsername": "",
        "userPassword": "",
        "confirmPassword": "",
        "userEmail": "",
        "userPhone": "",

    });

    const [loading, setLoading] = useState(false);
    const avatar = useRef(); 
    let nav = useNavigate();

    const change = (evt, field) => {
        setUser(current => {
            return {...current, [field]: evt.target.value}
        })
    }

    const register = (evt) => {
        evt.preventDefault();

        const process = async () => {
            let formData = new FormData();
            for(let field in user)
                if(field !== "confirmPassword")
                    formData.append(field, user[field]);
            formData.append("avatar", avatar.current.files[0]) ;   
            
            setLoading(true);
            let res = await Apis.post(endpoints['register'], formData);

            if(res.status === 201){
                nav("/login");
            }
        }

        if(user.userPassword !== user.confirmPassword){

        }else {
            process();
        }
    }
    return <>
        <h1 className="text-center text-info">ĐĂNG KÝ NGƯỜI DÙNG</h1>
        <Form onSubmit={register}>
            <Form.Group className="mb-3" >
                <Form.Label>Tên đăng nhập</Form.Label>
                <Form.Control type="text" value ={user.userUsername} onChange={e=> change(e, "userUsername")} placeholder="Tên đăng nhập" />
            </Form.Group>
            <Form.Group className="mb-3" >
                <Form.Label>Mật khẩu</Form.Label>
                <Form.Control type="password" value ={user.userPassword} onChange={e=> change(e, "userPassword")} placeholder="Mật khẩu" />
            </Form.Group>
            <Form.Group className="mb-3" >
                <Form.Label>Xác nhận mật khẩu</Form.Label>
                <Form.Control type="password" value ={user.confirmPassword} onChange={e=> change(e, "confirmPassword")} placeholder="Xác nhận mật khẩu" />
            </Form.Group>
            <Form.Group className="mb-3" >
                <Form.Label>Email</Form.Label>
                <Form.Control type="email" value ={user.userEmail} onChange={e=> change(e, "userEmail")} placeholder="Email" />
            </Form.Group>
            <Form.Group className="mb-3" >
                <Form.Label>Số điện thoại</Form.Label>
                <Form.Control type="number" value ={user.userPhone} onChange={e=> change(e, "userPhone")} placeholder="Số điện thoại" />
            </Form.Group>
            <Form.Group className="mb-3" >
                <Form.Label>Ảnh đại diện</Form.Label>
                <Form.Control type="file" ref={avatar} />
            </Form.Group>
            <Form.Group className="mb-3" >
                {loading === true?<MySpinner/>:<Button type="submit" variant="danger">Đăng ký</Button>}
            </Form.Group>
        </Form>
    </>
}

export default Register;