import instance from "./customize-axios";

const getAllStatus = () => {
    return instance.get(`/api/v1/status`);
}
export  {getAllStatus};