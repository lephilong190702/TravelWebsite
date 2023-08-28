import { useContext, useEffect, useState } from "react";
import { Button, Col, Container, Form, Nav, NavDropdown, Navbar, Row } from "react-bootstrap";
import Apis, { endpoints } from "../configs/Apis";
import MySpinner from "./MySpinner";
import { Link, useNavigate } from "react-router-dom";
import { MyUserContext } from "../App";

const Header = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [destinations, setDestinations] = useState([]);
    const [kw, setKw] = useState("");
    const nav = useNavigate();

    useEffect(() => {
        const loadDes = async () => {
            let res = await Apis.get(endpoints['destinations']);

            setDestinations(res.data);
        }

        loadDes();
    }, [])

    const search = (evt) => {
        evt.preventDefault();
        nav(`/?kw=${kw}`)
    }

    const logout = () => {
        dispatch({
            "type" : "logout"
        })
    }

    if (destinations === null)
        return <MySpinner />
    return (
        <>
            <Navbar expand="lg" className="bg-body-tertiary">
                <Container>
                    <Navbar.Brand href="#">Travel Website</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Link to="/" className="nav-link">Trang chủ</Link>
                            <Link to="/news" className="nav-link">Tin tức</Link>
                            <NavDropdown title="Điểm đến" id="basic-nav-dropdown">

                                {destinations.map(d => {
                                    let h = `/?desId=${d.destinationId}`;
                                    return <Link to={h} className="dropdown-item" key={d.destinationId}>{d.destinationCity}</Link>
                                })}

                            </NavDropdown>
                            {user == null ? <>
                                <Link className="text-danger nav-link" to="/login">Đăng nhập</Link>
                                <Link className="text-danger nav-link" to="/register">Đăng ký</Link>
                            </> : <>
                                <Link className="text-danger nav-link" to="/login">Chào {user.userUsername}!</Link>
                                <Button variant="secondary" onClick={logout}>Đăng xuất</Button>
                            </>}
                        </Nav>
                    </Navbar.Collapse>
                    <Form onSubmit={search} inline="">
                        <Row>
                            <Col xs="auto">
                                <Form.Control
                                    type="text"
                                    value={kw}
                                    onChange={e => setKw(e.target.value)}
                                    placeholder="Nhập từ khóa..." name="kw"
                                    className=" mr-sm-2"
                                />
                            </Col>
                            <Col xs="auto">
                                <Button type="submit">Tìm</Button>
                            </Col>
                        </Row>
                    </Form>
                </Container>

            </Navbar>
        </>
    )
}

export default Header;