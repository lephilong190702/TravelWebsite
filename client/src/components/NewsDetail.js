import { useContext, useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import Apis, { authApi, endpoints } from "../configs/Apis";
import MySpinner from "../layout/MySpinner";
import { MyUserContext } from "../App";
import { Button, Form, ListGroup } from "react-bootstrap";
import Moment from "react-moment";

const NewsDetail = () => {
    const [user,] = useContext(MyUserContext);
    const [news, setNews] = useState(null);
    const { id } = useParams();
    const [comments, setComments] = useState(null);
    const [content, setContent] = useState();
    const [currentPage, setCurrentPage] = useState(1);
    const commentsPerPage = 5;

    useEffect(() => {
        const loadNews = async () => {
            let { data } = await Apis.get(endpoints['news-detail'](id));
            setNews(data);
        }

        const loadComments = async () => {
            let { data } = await Apis.get(endpoints['news-comments'](id));
            setComments(data);
        }

        loadNews();
        loadComments();
    }, [])

    const addComment = async () => {
        try {
            let { data } = await authApi().post(endpoints["add-comment"], {
                "commentContent": content,
                "newsId": news.newsId
            });

            setComments([data, ...comments]);
            setContent("");
        } catch (error) {
            console.error("EX" + error);
        }
    }

    

    const handlePageClick = (pageNumber) => {
        setCurrentPage(pageNumber);
        console.log("Current Page:", pageNumber);
    };

    if (news == null || comments == null )
    return <MySpinner />

    let url = `/login?next=/news/${id}`;
    const indexOfLastComment = currentPage * commentsPerPage;
    const indexOfFirstComment = indexOfLastComment - commentsPerPage;
    const currentComments = comments.slice(indexOfFirstComment, indexOfLastComment);

    return (
        <>
            <h1 className="text-center text-info">{news.newsTitle}</h1>
            <img src={news.newsImageurl} alt={`${news.newsTitle}`} width="100%" height="700px"/>
            <p>{news.newsContent}</p>
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

export default NewsDetail;