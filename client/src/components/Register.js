import {  useRef, useState } from "react";
import { Alert, Button, Form } from "react-bootstrap";
import Apis, { endpoints } from "../configs/Apis";
import { useNavigate } from "react-router-dom";
import MySpinner from "../layout/MySpinner";


const Register = () => {
    const [user, setUser] = useState({
        "userUsername": "",
        "userPassword": "",
        "confirmPassword": "",
        "userEmail": "",
        "userPhone": "",

    });
    const [err, setErr] = useState(null);
    const [loading, setLoading] = useState(false);
    const avatar = useRef(); 
    let nav = useNavigate();

    const change = (evt, field) => {
        setUser(current => {
            return {...current, [field]: evt.target.value}
        });
        if (field === "userPhone" && evt.target.value.length > 10) {
            setErr("Số điện thoại không được vượt quá 10 ký tự.");
        } else {
            setErr(null); // Xóa thông báo lỗi
        }
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
            try {
                let res = await Apis.post(endpoints['register'], formData);

                if(res.status === 201){
                    nav("/login");
                } else {
                    setErr("Hệ thống bị lỗi!");
                }
            } catch (error) {
                setErr(error.response.data)
            }
            
        }

        if(user.userPassword === user.confirmPassword){
            process();
        }else {
            setErr("Mật khẩu KHÔNG khớp!");
        }
    }
    return <>
        <h1 className="text-center text-info">ĐĂNG KÝ NGƯỜI DÙNG</h1>
        {err === null?"":<Alert variant="danger">{err}</Alert>}
        <Form onSubmit={register}>
            <Form.Group className="mb-3" >
                <Form.Label>Tên đăng nhập</Form.Label>
                <Form.Control type="text" value ={user.userUsername} onChange={e=> change(e, "userUsername")} placeholder="Tên đăng nhập" required />
            </Form.Group>
            <Form.Group className="mb-3" >
                <Form.Label>Mật khẩu</Form.Label>
                <Form.Control type="password" value ={user.userPassword} onChange={e=> change(e, "userPassword")} placeholder="Mật khẩu" required />
            </Form.Group>
            <Form.Group className="mb-3" >
                <Form.Label>Xác nhận mật khẩu</Form.Label>
                <Form.Control type="password" value ={user.confirmPassword} onChange={e=> change(e, "confirmPassword")} placeholder="Xác nhận mật khẩu" required/>
            </Form.Group>
            <Form.Group className="mb-3" >
                <Form.Label>Email</Form.Label>
                <Form.Control type="email" value ={user.userEmail} onChange={e=> change(e, "userEmail")} placeholder="Email" required/>
            </Form.Group>
            <Form.Group className="mb-3" >
                <Form.Label>Số điện thoại</Form.Label>
                <Form.Control type="text" value ={user.userPhone} onChange={e=> change(e, "userPhone")} placeholder="Số điện thoại" required/>
            </Form.Group>
            <Form.Group className="mb-3" >
                <Form.Label>Ảnh đại diện</Form.Label>
                <Form.Control type="file" ref={avatar} required />
            </Form.Group>
            <Form.Group className="mb-3" >
                {loading === true?<MySpinner/>:<Button type="submit" variant="danger">Đăng ký</Button>}
            </Form.Group>
        </Form>
    </>
}

export default Register;