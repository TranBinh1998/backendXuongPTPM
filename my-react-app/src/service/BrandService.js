import instance from "./customize-axios";

const getAllBrand = () => {
    return instance.get(`/api/v1/brand`);
}

export  {getAllBrand};