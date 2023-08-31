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
import 'moment/locale/vi';
import NewsDetail from "./components/NewsDetail";
import MyCartCounterReducer from "./reducers/MyCartCounterReducer";
import Cart from "./components/Cart";

export const MyUserContext = createContext();
export const MyCartContext = createContext();
const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user") || null)
  const [cartCounter, cartDispatch] = useReducer(MyCartCounterReducer, 0);
  return (
    <MyUserContext.Provider value={[user, dispatch]}>
      <MyCartContext.Provider value={[cartCounter, cartDispatch]}>
        <BrowserRouter>
          <Header />
          <Container>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/news" element={<News />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
              <Route path="/tours/:id" element={<TourDetail />} />
              <Route path="/news/:id" element={<NewsDetail />} />
              <Route path="/cart" element={<Cart />} />
            </Routes>
          </Container>
          <Footer />
        </BrowserRouter>
      </MyCartContext.Provider>
    </MyUserContext.Provider>
  )
}

export default App;
