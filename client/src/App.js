import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layout/Header";
import Footer from "./layout/Footer";
import Home from "./components/Home";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container } from "react-bootstrap";
import Login from "./components/Login";
import { createContext, useReducer } from "react";
import MyUserReducer from "./reducers/MyUserReducer";
import cookie from "react-cookies";
import News from "./components/News";
import TourDetail from "./components/TourDetail";
import Register from "./components/Register";

export const MyUserContext = createContext();
const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user") ||  null)
  return (
    <MyUserContext.Provider value={[user, dispatch]}>
      <BrowserRouter>
        <Header />
        <Container>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/news" element={<News />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/tours/:id" element={<TourDetail/>} />
          </Routes>
        </Container>
        <Footer />
      </BrowserRouter>
    </MyUserContext.Provider>
  )
}

export default App;
