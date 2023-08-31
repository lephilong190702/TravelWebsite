import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = "/TravelWebsite";
const SERVER = "http://localhost:8080"

export const endpoints = {
    "destinations": `${SERVER_CONTEXT}/api/destinations/`,
    "tours": `${SERVER_CONTEXT}/api/tours/`,
    "news":`${SERVER_CONTEXT}/api/news/`,
    "login":`${SERVER_CONTEXT}/api/login/`,
    "register":`${SERVER_CONTEXT}/api/users/`,
    "current-user":`${SERVER_CONTEXT}/api/current-user/`,
    "tour-detail": (id) => `${SERVER_CONTEXT}/api/tours/${id}/`,
    "news-detail": (id) => `${SERVER_CONTEXT}/api/news/${id}/`,
    "tour-comments": (id) => `${SERVER_CONTEXT}/api/tours/${id}/comments/`,
    "news-comments": (id) => `${SERVER_CONTEXT}/api/news/${id}/comments/`,
    "add-comment":`${SERVER_CONTEXT}/api/comments/`,
    "pay":`${SERVER_CONTEXT}/api/pay/`
}

export const authApi = () => {
    return axios.create({
        baseURL: SERVER,
        headers: {
            "Authorization": cookie.load("token")
        }
    })
}
export default axios.create({
    baseURL: SERVER
})