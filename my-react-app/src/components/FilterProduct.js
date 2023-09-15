import React, {Component, useState} from 'react';
import BrandListSelect from "./BrandListSelect";
import SubCategorySelect from "./SubCategorySelect";
import StatusSelect from "./StatusSelect";
import {filterProduct} from "../service/ProducrService";

const FilterProduct = (props) => {

    const {productResultFitlter} = props;

    const [productName, setProductName] = useState('');

    const [sellPrice, setSellPrice] = useState();

    const [idBrand, setIdBrand] = useState();

    const [idSubCategory, setIdSubCategory] = useState();

    const [idStatus, setIdStatus] = useState();





    const productFilterRequest = {
        productName: productName,
        sellPrice: sellPrice,
        idBrand: idBrand,
        idSubCategory: idSubCategory,
        idStatus: idStatus
    }

    const handlerSearch = async () => {

        await filterProduct(productFilterRequest)
            .then((res) => {
                    productResultFitlter(res.data);

                },
                (err) => { // Xử lý kết quả thất bại
                    alert("Không tìm thấy sản phẩm phù hợp");
                }
            );


    }

    const handleChangeSubCategory = (e) => {
        const subCategoryId = e.target.value;
        setIdSubCategory(subCategoryId);
    }

    const handleChangeStatus = (e) => {
        const statusId = e.target.value;
        setIdStatus(statusId);
    }

    const handleChangeBrand = (e) => {
        const branId = e.target.value;
        setIdBrand(branId);
    };


    return (
        <>
            <div className={"row align-items-center justify-content-around"}>
                <div className="form-group col-sm-2">
                    <label>Name: </label>
                    <input type="text" className="form-control"
                           value={productName} onChange={event => setProductName(event.target.value)}
                           placeholder=""/>
                </div>
                <div className="form-group col-sm-2">
                    <label>Price: </label>
                    <input type="text" className="form-control"
                           value={sellPrice} onChange={event => setSellPrice(event.target.value)}
                           placeholder=""/>
                </div>
                <div className="form-group col-sm-2">
                    <label>Brand: </label>
                    <BrandListSelect idBrand={idBrand} onChangeSelect={handleChangeBrand}/>
                </div>
                <div className="form-group col-sm-2">
                    <label>SubCategory: </label>
                    <SubCategorySelect
                        idSubCategory={idSubCategory}
                        onchangeSelect={handleChangeSubCategory}
                    />

                </div>
                <div className="form-group col-sm-2">
                    <label>Status: </label>
                    <StatusSelect idStatus={idStatus} onchangeSelect={handleChangeStatus}/>

                </div>
                <div className="form-group col-sm-2">
                    <button onClick={() => handlerSearch()} className={"btn btn-search-icon btn-sm btn-success"}><i
                        className="fa-solid fa-magnifying-glass"></i></button>

                </div>

            </div>
        </>

    );

}

export default FilterProduct;