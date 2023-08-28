import { useEffect, useState } from "react"
import { Alert, Button, Card, Col, Form, Row } from "react-bootstrap";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import Apis, { endpoints } from "../configs/Apis";
import MySpinner from "../layout/MySpinner";

const Home = () => {
    const [tours, setTours] = useState(null);
    const [q] = useSearchParams();
    const [kw, setKw] = useState("");
    const [fromPrice, setFromPrice] = useState(""); // Giá thấp nhất
    const [toPrice, setToPrice] = useState(""); // Giá cao nhất
    const [fromDate, setFromDate] = useState(""); // Ngày
    const [toDate, setToDate] = useState(""); // Ngày
    const nav = useNavigate();

    useEffect(() => {
        const loadTours = async () => {
            try {
                let e = endpoints['tours'];
                let desId = q.get("desId");
                let hasParams = false; // Biến để kiểm tra xem đã thêm tham số nào vào URL chưa

                if (desId !== null) {
                    e += `?desId=${desId}`;
                    hasParams = true;
                }

                let kw = q.get("kw");
                if (kw !== null) {
                    e += hasParams ? `&kw=${kw}` : `?kw=${kw}`;
                    hasParams = true;
                }

                let fromPrice = q.get("fromPrice");
                if (fromPrice !== null) {
                    e += hasParams ? `&fromPrice=${fromPrice}` : `?fromPrice=${fromPrice}`;
                    hasParams = true;
                }

                let toPrice = q.get("toPrice");
                if (toPrice !== null) {
                    e += hasParams ? `&toPrice=${toPrice}` : `?toPrice=${toPrice}`;
                    hasParams = true;
                }

                let fromDate = q.get("fromDate");
                if (fromDate !== null) {
                    e += hasParams ? `&fromDate=${fromDate}` : `?fromDate=${fromDate}`;
                    hasParams = true;
                }

                let toDate = q.get("toDate");
                if (toDate !== null) {
                    e += hasParams ? `&toDate=${toDate}` : `?toDate=${toDate}`;
                    hasParams = true;
                }

                let res = await Apis.get(e);
                setTours(res.data);
            } catch (ex) {
                console.error(ex);
            }
        }

        loadTours();

    }, [q]);

    const search = (evt) => {
        evt.preventDefault();
        // Xây dựng URL tìm kiếm với các tham số giá thấp nhất, giá cao nhất và ngày.

        if (!kw && !fromPrice && !toPrice && !fromDate && !toDate) {
            alert("Vui lòng nhập ít nhất một tham số tìm kiếm.");
            return;
        }

        let url = "/?"; // Khởi tạo URL tìm kiếm

        // Kiểm tra và thêm các tham số vào URL nếu có giá trị
        if (kw) {
            url += `kw=${kw}&`;
        }
        if (fromPrice) {
            url += `fromPrice=${fromPrice}&`;
        }
        if (toPrice) {
            url += `toPrice=${toPrice}&`;
        }
        if (fromDate) {
            url += `fromDate=${fromDate}&`;
        }
        if (toDate) {
            url += `toDate=${toDate}&`;
        }

        // Loại bỏ dấu & cuối cùng nếu có
        if (url.charAt(url.length - 1) === "&") {
            url = url.slice(0, -1);
        }

        // Sử dụng `nav` để chuyển hướng đến URL tìm kiếm
        nav(url);
    }

    if (tours === null)
        return <MySpinner />

    if (tours.length === 0)
        return <Alert variant="info" className="mt-1">Không tìm thấy tour nào!</Alert>

    return (
        <>
            <h1 className="text-center text-info mb-5">DANH SÁCH TOUR</h1>

            <Row>
                <Col xs={12} md={4}>
                    <Form onSubmit={search} inline style={{ backgroundColor: '#f5f5f5', padding: '20px', borderRadius: '10px' }} >
                        <Row>
                            <Col xs="auto" className="mb-3">
                                <Form.Group>
                                    <Form.Label>Tên tour:</Form.Label>
                                    <Form.Control
                                        type="text"
                                        value={kw}
                                        onChange={e => setKw(e.target.value)}
                                        placeholder="Nhập tên tour..."
                                        name="kw"
                                        className="mr-sm-2"
                                    />
                                </Form.Group>
                            </Col>
                            <Col xs="auto" className="mb-3">
                                <Form.Group>
                                    <Form.Label>Giá thấp nhất:</Form.Label>
                                    <Form.Control
                                        type="number"
                                        value={fromPrice}
                                        onChange={e => setFromPrice(e.target.value)}
                                        placeholder="Nhập giá thấp nhất..."
                                        className="mr-sm-2"
                                    />
                                </Form.Group>
                            </Col>
                            <Col xs="auto" className="mb-3">
                                <Form.Group>
                                    <Form.Label>Giá cao nhất:</Form.Label>
                                    <Form.Control
                                        type="number"
                                        value={toPrice}
                                        onChange={e => setToPrice(e.target.value)}
                                        placeholder="Nhập giá cao nhất..."
                                        className="mr-sm-2"
                                    />
                                </Form.Group>
                            </Col>
                            <Row>
                                <Col xs="auto" className="mb-3">
                                    <Form.Group>
                                        <Form.Label>Ngày bắt đầu:</Form.Label>
                                        <Form.Control
                                            type="date"
                                            value={fromDate}
                                            onChange={e => setFromDate(e.target.value)}
                                            placeholder="Nhập ngày bắt đầu..."
                                            className="mr-sm-2"
                                        />
                                    </Form.Group>
                                </Col>
                                <Col xs="auto" className="mb-3">
                                    <Form.Group>
                                        <Form.Label>Ngày kết thúc:</Form.Label>
                                        <Form.Control
                                            type="date"
                                            value={toDate}
                                            onChange={e => setToDate(e.target.value)}
                                            placeholder="Nhập ngày kết thúc..."
                                            className="mr-sm-2"
                                        />
                                    </Form.Group>
                                </Col>
                            </Row>

                            <Col xs="auto" className="mb-3">
                                <Button type="submit" className="mt-3">
                                    Tìm kiếm
                                </Button>
                            </Col>
                        </Row>
                    </Form>
                </Col>
                <Col xs={12} md={8}>
                    {tours.map(t => {
                        const image = t.imageSet.map(img => img.imageUrl)
                        return (
                            <Card key={t.tourId} className="mb-3">
                                <Row>
                                    <Col md={4}>

                                        <Card.Img variant="top" src={image[image.length - 1]} width="100%" height="100%" />

                                    </Col>
                                    <Col md={8}>
                                        <Card.Body>
                                            <Card.Title>{t.tourTitle}</Card.Title>
                                            <Card.Text>{t.tourPrice} VNĐ</Card.Text>
                                            <Link to={`/tours/${t.tourId}`}>
                                                <   Button variant="success">Xem thông tin</Button>
                                            </Link>
                                        </Card.Body>
                                    </Col>
                                </Row>
                            </Card>
                        );
                    })}
                </Col>
            </Row>
        </>
    )
}

export default Home;