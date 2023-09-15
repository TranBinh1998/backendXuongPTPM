import instance from "./customize-axios";

const getAllProducts = () => {
    return instance.get(`/api/v1/product`);
}

const delProduct = (id) => {
    return instance.delete(`/api/v1/product/del/${id}`)
}

const addNewProduct = (product) => {
    return instance.post(`/api/v1/product/save-product`, product, {
        headers: {
            'Content-Type': 'application/json'
        }
    } );
}

const filterProduct = (productFilter) => {
    return instance.post("/api/v1/product/filter-product", productFilter);

}

const searchProductByQuery = (query) => {
    return instance.get(`/api/v1/product/search?query=${query}`);
}

export  {getAllProducts, addNewProduct, delProduct, filterProduct, searchProductByQuery};