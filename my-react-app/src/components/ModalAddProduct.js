import React, {Component, useEffect, useState} from 'react';
import {Button, Modal} from 'react-bootstrap';
import BrandListSelect from "./BrandListSelect";
import SubCategorySelect from "./SubCategorySelect";
import {addNewProduct} from "../service/ProducrService";
import StatusSelect from "./StatusSelect";
import {toast} from "react-toastify";

const ModalAddProduct = (props) => {

    const [productName, setProductName] = useState('');

    const [color, setColor] = useState('');


    const [quantity, setQuantity] = useState();

    const [sellPrice, setSellPrice] = useState();

    const [originPrice, setOriginPrice] = useState();

    const [description, setDescription] = useState();

    const [idBrand, setIdBrand] = useState();

    const [idSubCategory, setIdSubCategory] = useState();

    const [idStatus, setIdStatus] = useState();

    const [idProduct, setIdProduct] = useState(0);

    const {show, handleClose, productEdit, modalState} = props;

    const [product, setProduct] = useState(null);

    const  [showModal, setShowModal] = useState(false);


    const editProduct = () => {
        if (product != null) {
            setIdProduct(product.id);
            setProductName(product.productName);
            setColor(product.color);
            setQuantity(product.quantity);
            setSellPrice(product.sellPrice);
            setOriginPrice(product.originPrice);
            setIdBrand(product.idBrand);
            setIdStatus(product.idStatus);
            setIdSubCategory(product.idSubCategory);

        }
        else {
            setIdProduct(0);
            setProductName('');
            setColor('');
            setQuantity(0);
            setSellPrice(0);
            setOriginPrice(0);
            setIdBrand(0);
            setIdStatus(0);
            setIdSubCategory(0);
        }

    }

    const handleChangeBrand = (e) => {
        const branId = e.target.value;
        setIdBrand(branId);
    };


    const handleChangeSubCategory = (e) => {
        const subCategoryId = e.target.value;
        setIdSubCategory(subCategoryId);

    }

    const handleChangeStatus = (e) => {
        const statusId = e.target.value;
        setIdStatus(statusId);
    }

    useEffect(() => {
        if (modalState == "edit" || modalState == "detail" ) {
            setProduct(productEdit);
            editProduct();
        } else if (modalState == "add") {
            setProduct(null);
            editProduct();
        }
    }, [modalState]);


    useEffect(() => { // Hàm này sẽ chạy khi product thay đổi
        if (product != null) { // Nếu product không phải null, thì set các input theo product
            setIdProduct(product.id);
            setProductName(product.productName);
            setColor(product.color);
            setQuantity(product.quantity);
            setSellPrice(product.sellPrice);
            setOriginPrice(product.originPrice);
            setDescription(product.description)
            setIdBrand(product.idBrand); setIdStatus(product.idStatus);
            setIdSubCategory(product.idSubCategory); }
        else { // Nếu product là null, thì set các input về giá trị mặc định
            setIdProduct(0);
            setProductName('');
            setColor(''); setQuantity(0);
            setSellPrice(0); setOriginPrice(0);
            setDescription('')
            setIdBrand(0);
            setIdStatus(0);
            setIdSubCategory(0); }
    }, [product]);


    const productReq = {
        id: idProduct,
        productName: productName,
        color: color,
        quantity: quantity,
        sellPrice: sellPrice,
        originPrice: originPrice,
        description:description,
        idBrand: idBrand,
        idSubCategory: idSubCategory,
        idStatus: idStatus
    }


    const handleSaveUser = async () => {

        if (productReq.productName.trim() == "") {
            alert("Vui lòng nhập tên sản phẩm");
            return false;
        }


        if (productReq.productName.length < 3 || productReq.productName > 20) {
            alert("Tên sản phẩm không được quá ngắn hoặc quá dài 3-20 ký tự");
            return  false;
        }
        if ((productReq.sellPrice  < 1000) || (productReq.originPrice  < 1000) ) {
            alert("Sản phẩm không thể dưới giá 1,000 vnd");
            return  false;
        }

        if (productReq.sellPrice < productReq.originPrice) {
            alert("Gía bán không nên nhỏ hơn giá gốc");
            return  false;
        }
        if (productReq.quantity < 1) {
            alert("Vui lòng nhập số lượng");
            return  false;
        }
        if (productReq.color.trim() == "") {
            alert("Vui lòng chọn màu. Nếu không màu hoặc không xác định nhập none ");
            return false;
        }

        if (productReq.description.trim() == "") {
            alert("Vui lòng nhập chi tiết sản phẩm");
            return  false;
        }

        if (productReq.idSubCategory === 0) {
            alert("Vui lòng chọn SubCategory")
            return  false;
        }
        if (productReq.idBrand === 0) {
            alert("Vui lòng chọn Brand");
        }

        if (productReq.idStatus === 0 || productReq.idStatus === "") {
            alert("Vui lòng chọn trạng thái ");
            return false;
        }




        try {
            let res = await addNewProduct(productReq);
            if (res.status === 201) {
                toast.success("Thêm sản phẩm thành công");
                // Sau khi add thành công thì truyền product ra component cha;
                props.addProduct(true);
                handleClose();
                setProduct(null);
            } else if (res.status === 200) {
                toast.success("Cập nhật sản phẩm thành công");
                props.addProduct(true);
                handleClose();
                setProduct(null);

            } else {
                toast.error(res.statusText);
            }
        }catch (res) {
            toast.error("Có lỗi xảy ra");
        }


    }

    return (
        <>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>{modalState == 'add' ? "Add Product": (modalState == "edit" ? "Update Product" : "Detail Product")}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div className={"body-add-new"}>
                        <div>
                            <div>
                                <div className="form-group">
                                    <label>Name : </label>
                                    <input type="text" className="form-control" id=""
                                           value={productName} onChange={event => setProductName(event.target.value)}
                                           aria-describedby="emailHelp" readOnly={modalState == "detail" ? true : false} placeholder="Enter Product Name"/>
                                </div>
                                <div className="form-group">
                                    <label>Color : </label>
                                    <input type="text" className="form-control"
                                           value={color} onChange={event => setColor(event.target.value)}
                                           readOnly={modalState == "detail" ? true : false}
                                           placeholder="Enter Color"/>
                                </div>
                                <div className="form-group">
                                    <label>Quantity : </label>
                                    <input type="number" className="form-control"
                                           value={quantity} onChange={event => setQuantity(event.target.value)}
                                           readOnly={modalState == "detail" ? true : false}
                                           placeholder="Enter Quantity Product"/>
                                </div>
                                <div className="form-group">
                                    <label>Sell Price : </label>
                                    <input type="number" className="form-control"
                                           value={sellPrice} onChange={event => setSellPrice(event.target.value)}
                                           readOnly={modalState == "detail" ? true : false}
                                           placeholder="Enter Sell Price Product"/>
                                </div>
                                <div className="form-group">
                                    <label>Origin Price : </label>
                                    <input type="number" className="form-control"
                                           readOnly={modalState == "detail" ? true : false}
                                           value={originPrice} onChange={event => setOriginPrice(event.target.value)}

                                           placeholder="Enter Origin Price Product"/>
                                </div>
                                <div className="form-group ">
                                    <label>Description : </label>
                                    <input type="text" className="form-control" id=""
                                           readOnly={modalState == "detail" ? true : false}
                                           value={description} onChange={event => setDescription(event.target.value)}
                                           aria-describedby="emailHelp" placeholder="Enter Description"/>
                                </div>
                                <div className="form-group">
                                    <label>Choose Brand : </label>
                                    <BrandListSelect idBrand={idBrand}  onChangeSelect={handleChangeBrand}/>
                                </div>

                                <div className="form-group">
                                    <label>Choose SubCategory : </label>
                                    <SubCategorySelect idSubCategory={idSubCategory}
                                                       onchangeSelect={handleChangeSubCategory}/>
                                </div>
                                <div className="form-group">
                                    <label>Choose SubCategory : </label>
                                    <StatusSelect idStatus={idStatus} onchangeSelect={handleChangeStatus}/>
                                </div>
                            </div>

                        </div>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button hidden={modalState == "detail" ? true : false} variant="success" onClick={() => handleSaveUser()}>
                        Save Product
                    </Button>
                </Modal.Footer>
            </Modal>

        </>
    );
}

export default ModalAddProduct;