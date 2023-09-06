import { useEffect, useState } from "react"
import { Alert, Button, Card, Col, Row } from "react-bootstrap";
import { Link, useSearchParams } from "react-router-dom";
import Apis, { endpoints } from "../configs/Apis";
import MySpinner from "../layout/MySpinner";

const News = () => {
    const [news, setNews] = useState(null);
    const [q] = useSearchParams();

    useEffect(() => {
        const loadNews = async () => {
            try {
                let e = endpoints['news'];
                let res = await Apis.get(e);
                setNews(res.data);
            } catch (ex) {
                console.error(ex);
            }
        }

        loadNews();

    }, [q]);


    if (news === null)
        return <MySpinner />

    if (news.length === 0)
        return <Alert variant="info" className="mt-1">Không tìm thấy tin tức nào!</Alert>

    return (
        <>
            <h1 className="text-center text-info">DANH SÁCH TIN TỨC</h1>
            <Row>
                {news.map(n => {
                    return <Col xs={12} md={3} className="mt-2">
                        <Card style={{ width: '18rem', height: '23rem' }}>
                            <Card.Img variant="top" src={n.newsImageurl} width="200" height="200" />
                            <Card.Body>
                                <Card.Title>{n.newsTitle}</Card.Title>
                                <Link to={`/news/${n.newsId}`}>
                                <Button variant="primary">Xem chi tiết</Button>
                                </Link>
                               
                            </Card.Body>
                        </Card>
                    </Col>
                })}

            </Row>
        </>
    )
}

export default News;