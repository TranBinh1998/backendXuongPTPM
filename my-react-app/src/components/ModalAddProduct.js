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

    const [errors, setErrors] = useState({})

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
            setErrors({})
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
            setErrors({})
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

        const validationErrors = {}


        if (productReq.productName.length < 3 || productReq.productName > 25) {
            validationErrors.productName = "Tên sản phẩm không được để trống hoặc quá ngắn 3-25 ký tự";
            setErrors(validationErrors);
        }
        if (productReq.color.trim() == "") {
            validationErrors.color = "Vui lòng chọn màu. Nếu không màu hoặc không xác định nhập none ";
            setErrors(validationErrors)
        }
        if (productReq.quantity < 1) {
            validationErrors.quantity = "Vui lòng nhập số lượng";
            setErrors(validationErrors);
        }

        if ((productReq.sellPrice  < 1000) ) {
            validationErrors.sellPrice = "Sản phẩm không thể dưới giá 1,000 vnd";
            setErrors(validationErrors)
        }
        if ( (productReq.originPrice  < 1000) ) {
            validationErrors.originPrice = "Sản phẩm không thể dưới giá 1,000 vnd";
            setErrors(validationErrors)
        }

        if (productReq.sellPrice < productReq.originPrice) {
            console.log(productReq.sellPrice);
            console.log(productReq.originPrice);
            validationErrors.sellPrice = "Gía bán không nên nhỏ hơn giá nhập !!! ";
            setErrors(validationErrors)
        }


        if (productReq.description.length < 1) {
            validationErrors.description = "Vui lòng nhập chi tiết sản phẩm !!! ";
            setErrors(validationErrors)
        }
        if (productReq.idBrand === 0) {
            validationErrors.idBrand = "Vui lòng chọn SubCategory !!! ";
            setErrors(validationErrors);
        }

        if (productReq.idSubCategory === 0) {
            validationErrors.idSubCategory = "Vui lòng chọn SubCategory !!! ";
            setErrors(validationErrors);
        }


        if (productReq.idStatus === 0 || productReq.idStatus === "") {
            validationErrors.idStatus = "Vui lòng chọn trạng thái !!! ";
            setErrors(validationErrors);
        }

        const isEmpty = Object.keys(validationErrors).length === 0; // true
        if (isEmpty == false) {
            console.log(isEmpty);
            console.log(validationErrors);
            return false;
        }

        try {
            let res = await addNewProduct(productReq).then(

            )

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
                console.log("res bắt được", res);
                toast.error(res.data.color); // Lấy data từ server gửi ra client
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
                                    {errors.productName && <span>{errors.productName}</span>}
                                </div>
                                <div className="form-group">
                                    <label>Color : </label>
                                    <input type="text" className="form-control"
                                           value={color} onChange={event => setColor(event.target.value)}
                                           readOnly={modalState == "detail" ? true : false}
                                           placeholder="Enter Color"/>
                                    {errors.color && <span>{errors.color}</span>}
                                </div>
                                <div className="form-group">
                                    <label>Quantity : </label>
                                    <input type="number" className="form-control"
                                           value={quantity} onChange={event => setQuantity(event.target.value)}
                                           readOnly={modalState == "detail" ? true : false}
                                           placeholder="Enter Quantity Product"/>
                                    {errors.quantity && <span>{errors.quantity}</span>}

                                </div>
                                <div className="form-group">
                                    <label>Sell Price : </label>
                                    <input type="number" className="form-control"
                                           value={sellPrice} onChange={event => setSellPrice(event.target.value)}
                                           readOnly={modalState == "detail" ? true : false}
                                           placeholder="Enter Sell Price Product"/>
                                    {errors.sellPrice && <span>{errors.sellPrice}</span>}

                                </div>
                                <div className="form-group">
                                    <label>Origin Price : </label>
                                    <input type="number" className="form-control"
                                           readOnly={modalState == "detail" ? true : false}
                                           value={originPrice} onChange={event => setOriginPrice(event.target.value)}

                                           placeholder="Enter Origin Price Product"/>
                                    {errors.originPrice && <span>{errors.originPrice}</span>}

                                </div>
                                <div className="form-group ">
                                    <label>Description : </label>
                                    <input type="text" className="form-control" id=""
                                           readOnly={modalState == "detail" ? true : false}
                                           value={description} onChange={event => setDescription(event.target.value)}
                                           aria-describedby="emailHelp" placeholder="Enter Description"/>
                                    {errors.description && <span>{errors.description}</span>}

                                </div>
                                <div className="form-group">
                                    <label>Choose Brand : </label>
                                    <BrandListSelect idBrand={idBrand}  onChangeSelect={handleChangeBrand}/>
                                    {errors.idBrand && <span>{errors.idBrand}</span>}
                                </div>

                                <div className="form-group">
                                    <label>Choose SubCategory : </label>
                                    <SubCategorySelect idSubCategory={idSubCategory}
                                                       onchangeSelect={handleChangeSubCategory}/>
                                    {errors.idSubCategory && <span>{errors.idSubCategory}</span>}

                                </div>
                                <div className="form-group">
                                    <label>Choose Status : </label>
                                    <StatusSelect idStatus={idStatus} onchangeSelect={handleChangeStatus}/>
                                    {errors.idStatus && <span>{errors.idStatus}</span>}
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