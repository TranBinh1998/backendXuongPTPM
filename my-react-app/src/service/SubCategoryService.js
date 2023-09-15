import instance from "./customize-axios";

const getAllSubCategory = () => {
    return instance.get(`/api/v1/subcategory`);
}

export {getAllSubCategory};

