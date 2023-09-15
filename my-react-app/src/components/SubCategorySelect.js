import React, {Component, useEffect, useState} from 'react';
import {getAllSubCategory} from "../service/SubCategoryService";

const SubCategorySelect = (props) => {

    const {idSubCategory, onchangeSelect} = props;

    const [subCategoryList, setSubCategory] = useState([]);



    useEffect(()=> {
        getListCategory(); // gọi hàm
    }, [])

    const getListCategory = async () => {

        let res = await getAllSubCategory();
        if (res) {
            setSubCategory(res.data);
        }else {
            alert("Có lỗi xảy ra")
        }
    }


        return (

                <>
                    <div className={"select-container"}>
                    <select value={idSubCategory} onChange={onchangeSelect} className={"form-control"}>
                        <option value={0} selected={true} >Chọn</option>
                        {subCategoryList.map(item => {
                            return (
                                <option key={item.id} value={item.id}>{item.subCateName}</option>
                            )
                        })}

                    </select>
                        <i className="fa-solid fa-chevron-down"></i>

                    </div>
                </>


        );


}

export default SubCategorySelect;