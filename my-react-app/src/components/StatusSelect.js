import React, {Component, useEffect, useState} from 'react';
import '../css/Fiter.scss';

import {getAllStatus} from "../service/StatusService";

const StatusSelect = (props) => {

    const {idStatus, onchangeSelect } = props;

    const [lisIdStatus, setListIdStatus] = useState([]);


    const getListStatus = async () => {

        let res = await getAllStatus();
        if (res) {
            setListIdStatus(res.data);
        }
    }

    useEffect( () => {
        getListStatus();
    },[]);


    return (
        <>
            <div className={"select-container"}>
            <select className={"form-control"} defaultValue={0} value={idStatus} onChange={onchangeSelect}>
                <option value={0} selected >Chọn
                </option>
                {lisIdStatus.map(item => {
                    return (

                        <option key={item.id} value={item.id}>{item.statusName}</option>
                    )
                })}
            </select>
            <i className="fa-solid fa-chevron-down"></i>
            </div>
        </>
    )
}


export default StatusSelect;