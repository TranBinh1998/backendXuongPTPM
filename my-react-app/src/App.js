
import './App.scss';
import Header from "./components/Header";
import TableProduct from "./components/TableProduct";
import Container from "react-bootstrap/Container";
import ModalAddProduct from "./components/ModalAddProduct";
import { ToastContainer, toast } from 'react-toastify';
import FilterProduct from "./components/FilterProduct";

function App() {
  return (
      <>
          <div className="app-container">
                <Header/>
                <Container>
                    <TableProduct/>
                </Container>
          </div>

          <ToastContainer
              position="top-right"
              autoClose={5000}
              hideProgressBar={false}
              newestOnTop={false}
              closeOnClick
              rtl={false}
              pauseOnFocusLoss
              draggable
              pauseOnHover
              theme="light"
          />

      </>

  );
}

export default App;
