import React, {Component, useEffect, useState} from 'react';
import {Table} from "react-bootstrap";
import {delProduct, getAllProducts, searchProductByQuery} from "../service/ProducrService";
import ModalAddProduct from "./ModalAddProduct";
import {toast} from "react-toastify";
import FilterProduct from "./FilterProduct";

const TableProduct = () => {
    const [products, setProducts] = useState([]);
    // Gọi hàm call api
    const [isShowModalAdd, setIsShowModalAdd] = useState(false);

    const [boolean, setBoolean] = useState (false);

    const [modalMode, setModalMode] = useState();

    const [productEdit,setProductEdit] = useState();

    const [querySearchProduct, setQuerySearchProduct] = useState(' ');

    const [productResultFitlter, setproductResultFitlter ] = useState();



    const handleClose = () =>  {
        setIsShowModalAdd(false);

        setModalMode('');
    }

    useEffect(() => {
        getProducts();
    }, [boolean]);


    const getProducts = async () => {
        let res = await getAllProducts();
        if (res) {
            setProducts(res.data);
            setBoolean(false);
        }
    }
    const addProduct = (boolean) => {
        // hàm để thêm sản phẩm vào danh sách
        setBoolean(true);

    }

    const handleModalAddProduct = () => {
        setProductEdit(null);
        setModalMode('add');
        setIsShowModalAdd(true);
    }

    const handleShowModalEditProduct = (item) =>{
        setProductEdit(item);
        setModalMode("edit");
        setIsShowModalAdd(true);

    }

    const handleModalDetailProduct = (item) =>{
        setProductEdit(item);
        setModalMode("detail");
        setIsShowModalAdd(true);

    }



    const handleSearchProduct = async () => {
        await searchProductByQuery(querySearchProduct).then(
            (res) => {
                if (res.data.length < 1) {
                    alert("Không tìm thấy sản phẩm phù hợp");
                }else {
                    setProducts(res.data);
                }
            }
        )

    }

    const handleFilter = (data) => {
        setProducts(data);

    }




    const  handleDeleteProduct = async (product) => {
        try {
            let  res = await delProduct(product.id)
            if (res.status === 202) {
                toast.success("Xóa thành công " +product.productName);
                setBoolean(true);
            }else {
                toast.error("Đã có lỗi xảy ra");
            }
        }catch (e) {
            toast.error("Đã có lỗi xảy ra");
        }
    }

    return (
        <>
            <FilterProduct productResultFitlter={handleFilter} />
            <div className={""}>
                <button className={"btn btn-success my-lg-5"} onClick={ () => handleModalAddProduct()}>Add Product</button>
            </div>
            <div className={"float-end mb-2"}>
                <span><b >SEARCH : </b></span>
                <input type={"text"} value={querySearchProduct} onChange={event => setQuerySearchProduct(event.target.value)} />
                <button onClick={() => handleSearchProduct()} className={"top-50 btn ms-3 btn-search-icon btn-sm btn-success"}>
                    <i className="fa-solid fa-magnifying-glass"></i>
                </button>
            </div>
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>
                        <span >No</span>
                    </th>
                    <th>
                        <span >
                            Produt name
                        </span>
                    </th>
                    <th >
                        <span>Brand Name</span>

                    </th>
                    <th>
                        <div>
                            Subcategory
                        </div>

                    </th>
                    <th>
                        <span>
                              Price
                        </span>

                    </th>
                    <th>
                        <div>
                            Status

                        </div>

                    </th>
                    <th>Function</th>
                </tr>
                </thead>
                <tbody>

                {products && products.length>0 && products.map((item, index) => {
                            return (
                                <tr key={item.id}>
                                    <td>{index+1}</td>
                                    <td>{item.productName}</td>
                                    <td>{item.nameBrand}</td>
                                    <td>{item.nameCategory}</td>
                                    <td>{item.sellPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'})}</td>
                                    <td>{item.status}</td>
                                    <td className={"d-flex align-items-center justify-content-evenly"}>
                                        <button onClick={ () => handleModalDetailProduct(item)}  className={"btn btn-primary"}>Detail</button>
                                        <button onClick={ () => handleShowModalEditProduct(item)} className={"btn btn-warning"}>Edit</button>
                                        <button onClick={ ()=> handleDeleteProduct(item)} className={"btn btn-danger"}>Del</button>

                                    </td>
                                </tr>
                            )
                            })}
                </tbody>
            </Table>

            <ModalAddProduct
                show = {isShowModalAdd}
                handleClose={handleClose}
                addProduct={addProduct}
                productEdit = {productEdit}
                modalState = {modalMode}
            />




        </>
    );
};

export default TableProduct;