import { useEffect, useState } from "react";
import Apis, { authApi, endpoints } from "../configs/Apis";
import { Link, useParams } from "react-router-dom";
import { Button, Image, ListGroup, Form } from "react-bootstrap";
import MySpinner from "../layout/MySpinner";
import { useContext } from "react";
import { MyCartContext, MyUserContext } from "../App";
import Moment from "react-moment";
import './Pagination.css';
import cookie from "react-cookies";
// import FbImageGrid from "react-fb-image-grid"


const TourDetail = () => {
    const [, cartDispatch] = useContext(MyCartContext);
    const [user,] = useContext(MyUserContext);
    const [tour, setTour] = useState(null);
    const { id } = useParams();
    const [comments, setComments] = useState(null);
    const [content, setContent] = useState();
    const [currentPage, setCurrentPage] = useState(1);
    const commentsPerPage = 5;
    // const [images, setImages] = useState(null);

    useEffect(() => {

        const loadTour = async () => {
            let { data } = await Apis.get(endpoints['tour-detail'](id));
            setTour(data);
        }

        const loadComments = async () => {
            try {
                let e = endpoints['tour-comments'](id);
                let res = await Apis.get(e);
                setComments(res.data);

            } catch (ex) {
                console.error("EX" + ex);
            }
        }

        loadTour();
        loadComments();

    }, [id, currentPage]);

    const addComment = async () => {
        try {
            let { data } = await authApi().post(endpoints["add-comment"], {
                "commentContent": content,
                "tourId": tour.tourId
            });

            setComments([data, ...comments]);
            setContent("");
        } catch (error) {
            console.error("EX" + error);
        }
    }


    if (tour == null || comments == null)
        return <MySpinner />

    let url = `/login?next=/tours/${id}`;
    const indexOfLastComment = currentPage * commentsPerPage;
    const indexOfFirstComment = indexOfLastComment - commentsPerPage;
    const currentComments = comments.slice(indexOfFirstComment, indexOfLastComment);

    const handlePageClick = (pageNumber) => {
        setCurrentPage(pageNumber);
        console.log("Current Page:", pageNumber);
    };

    const order = (tour, ticketType) => {
        cartDispatch({
            "type": "inc",
            "payload": 1
        });

        let cart = cookie.load("cart") || null;
        if (cart === null)
            cart = {}

        if (tour.tourId in cart) {
            if (ticketType === "adult") {
                cart[tour.tourId]["adultQuantity"]++;
            } else if (ticketType === "child") {
                cart[tour.tourId]["childQuantity"]++;
            }
        } else {
            cart[tour.tourId] = {
                "id": tour.tourId,
                "name": tour.tourTitle,
                adultQuantity: ticketType === "adult" ? 1 : 0,
                childQuantity: ticketType === "child" ? 1 : 0,
                "adultUnitPrice": tour.tourAdultPrice,
                "childUnitPrice": tour.tourChildPrice
            }
        }

        cookie.save("cart", cart);
    }


    return (
        <>
            <h1 className="text-center text-info">{tour.tourTitle}</h1>
            {/* <div>
                    {images !== null &&
                        <FbImageGrid
                            images={images}
                            hideOverlay
                            imageWidth={250}
                            imageHeight={250}
                          
                        />}
                
                    </div> */}
            <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                {tour.imageSet !== null && tour.imageSet.map(t => (
                    <Image src={t.imageUrl} width={200} height={200} />
                ))}
            </div>
            <p>{tour.tourDescription}</p>
            <div className="mt-2 mb-2">
                <span>NGƯỜI LỚN: {tour.tourAdultPrice} VND  </span>
                <Button onClick={() => order(tour, "adult")}>ĐẶT VÉ</Button>
            </div>
            <div className="mt-2 mb-2 ">
                <span>TRẺ EM: {tour.tourChildPrice} VND  </span>
                <Button onClick={() => order(tour, "child")}>ĐẶT VÉ</Button>
            </div>
            <hr />
            {user === null ? <p>Vui lòng <Link to={url}>đăng nhập</Link> để bình luận! </p> : <>
                <Form.Control as="textarea" aria-label="With textarea" value={content} onChange={e => setContent(e.target.value)} placehoder="Nhập nội dung bình luận" />
                <Button onClick={addComment} variant="info" className="mt-2">Bình luận</Button>
            </>}
            <hr />
            <ListGroup>
                {currentComments.map(c => <ListGroup.Item id={c.commentId}>
                    <div>
                        <div>
                            <img src={c.userId.userImageurl} alt={`${c.userId.userUsername} avatar`}
                                style={{
                                    borderRadius: '50%',
                                    width: '50px',
                                    height: '50px',
                                }}
                            />
                            <span> {c.commentContent} </span>

                        </div>
                        <div>

                        </div>
                        <div>
                            <span style={{ fontWeight: 'bold' }}> {c.userId.userUsername}</span>
                            <span> đã bình luận </span>
                            <span>
                                <Moment locale="vi" fromNow>{c.commentDatetime}</Moment>
                            </span>
                        </div>
                    </div>
                </ListGroup.Item>)}
            </ListGroup>
            <div className="pagination">
                {Array.from({ length: Math.ceil(comments.length / commentsPerPage) }, (_, i) => (
                    <button
                        key={i}
                        onClick={() => handlePageClick(i + 1)}
                        className={i + 1 === currentPage ? "active" : ""}
                    >
                        {i + 1}
                    </button>
                ))}
            </div>
        </>
    )
}
export default TourDetail;