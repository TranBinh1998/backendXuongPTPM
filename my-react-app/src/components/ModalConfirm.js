import {Button,Modal } from 'react-bootstrap';
import {deleteUser} from "../Service/UserService";
import {toast} from "react-toastify";


const ModalConfirm = (props) => {
    const {show,handleClose,dataDelete} = props;

    const confirmDelete = async () => {
        let res = await deleteUser(dataDelete.id);

        if (res.statusCode === 204) {
            toast.success("Đã xóa thành công");
            handleClose();
        }else if (res.statusCode === 400) {
            toast.warning("Bạn không thể xóa user này");
            handleClose();
        }else  {
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
                    <Modal.Title>Delete a User</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div className={"body-add-new"}>
                        This action cant be undone !
                        Do you want delete this User ? <br/>
                        <b>Email : {dataDelete.email}</b>
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