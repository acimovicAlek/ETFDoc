import React,{ Component ,PropTypes} from 'react'
import axios,{post} from 'axios';
import Modal from 'react-responsive-modal';
class Upload extends Component {

  constructor(props) {
    super(props);
    this.state ={
      file:null,
      open: false
    }
    this.onFormSubmit = this.onFormSubmit.bind(this)
    this.onChange = this.onChange.bind(this)
    this.fileUpload = this.fileUpload.bind(this)
    this.onOpenModal = this.onOpenModal.bind(this)
    this.onCloseModal = this.onCloseModal.bind(this)
  }
  onFormSubmit(e){
    e.preventDefault() // Stop form submit
    this.fileUpload(this.state.file);
  }
  onChange(e) {
    this.setState({file:e.target.files[0]})
  }

  fileUpload(file){
    const formData = new FormData();
    formData.append('file',file)

  }

  onOpenModal = () => {
    this.setState({ open: true });
  };

  onCloseModal = () => {
    this.setState({ open: false });
  };

  render() {
    return (

      <div className="example">
      <div className="add-file-wrapper" style={{marginTop: "-15px"}}>
        <button className="btn btn-primary add-file-btn" onClick={this.onOpenModal}>
          <span className="glyphicon glyphicon-btn glyphicon-folder-open"></span><br></br>Upload file
        </button>
      </div>
        <Modal open={this.state.open} onClose={this.onCloseModal} little>
        <div  className="upload-modal">
          <h2 className="title">Upload new file</h2>
          <form onSubmit={this.onFormSubmit}>
            <input type="file" onChange={this.onChange} />
            <button type="submit" className="btn btn-primary submit-upload">Upload</button>
          </form>
        </div>
        </Modal>
      </div>
    );
  }
};



export default Upload;
