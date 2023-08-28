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
    "tourDetail": (id) => `${SERVER_CONTEXT}/api/tours/${id}`
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