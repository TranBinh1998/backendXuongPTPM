import {Button,Modal } from 'react-bootstrap';

import {toast} from "react-toastify";
import {delProduct} from "../service/ProducrService";


const ModalConfirm = (props) => {
    const {show,handleClose,dataDelete,onChangeBoolean} = props;

    const confirmDelete = async () => {
        try {
            let  res = await delProduct(dataDelete.id)
            if (res.status === 202) {
                toast.success("Xóa thành công " +dataDelete.productName);
                handleClose();
                onChangeBoolean(true)
            }else {
                toast.error("Đã có lỗi xảy ra");
            }
        }catch (e) {
            toast.error("Đã có lỗi xảy ra");
        }
    }


    return (
        <>
            <Modal show={show}
                   onHide={handleClose}
                   backdrop={"static"}
                   keyboard={false}
            >

                <Modal.Header closeButton>
                    <Modal.Title>Delete a Product</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div className={"body-add-new"}>
                        This action cant be undone !
                        Do you want delete  <b>{dataDelete.productName}</b>  ? <br/>

                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={() => confirmDelete()}>
                        Confirm
                    </Button>
                </Modal.Footer>
            </Modal>
        </>


    )


}
export default ModalConfirm;