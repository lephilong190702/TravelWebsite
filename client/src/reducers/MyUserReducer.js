import cookie from "react-cookies";

const MyUserReducer = (currenState, action) => {
    switch (action.type) {
        case "login":
            return action.payload;
        case "logout":
            cookie.remove("token");
            cookie.remove("user");
            return null;
        default:
            return currenState;
    }
}

export default MyUserReducer;