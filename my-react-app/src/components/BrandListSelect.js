import React, {Component, useEffect, useState} from 'react';
import {getAllBrand} from "../service/BrandService";

const BrandListSelect = (props) => {

    const {idBrand, onChangeSelect } = props;

    const [listBrand, setListBrand] = useState([]);




    const getListBrand = async () => {

        let res = await getAllBrand();
        if (res) {
            setListBrand(res.data);
        }
    }

    useEffect( () => {
        getListBrand();
    },[]);

    return (
        <>
            <div className={"select-container"}>


            <select className={"form-control"} value={idBrand} onChange={onChangeSelect}>
                <option value={0}>Chọn</option>
                {listBrand.map(item => {
                    return (
                        <option key={item.id}  value={item.id}>{item.brandName}</option>
                    )
                })}
            </select>
                <i className="fa-solid fa-chevron-down"></i>

            </div>
        </>
    )
}


export default BrandListSelect;