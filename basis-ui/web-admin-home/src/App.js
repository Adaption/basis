import React, { Component } from 'react';
// import logo from './logo.svg';
import logo from './img/logo_3.png';
import './style.css';


class Topbar extends Component{
  render(){
    return(
      // <table className='top-nav'>
      //   <tbody>
      //     <tr>
      //       <td>
      //         <img className='logo' src={logo} />
      //       </td>
      //       <td>POS</td>
      //       <td>WEB</td>
      //       <td>OMNI CHANNEL</td>
      //       <td>BẢNG GIÁ</td>
      //       <td>THÊM</td>
      //       <td>Trợ giúp</td>
      //       <td>Đăng nhập</td>
      //       <td>
      //         <button>
      //           Dùng thử
      //         </button>
      //       </td>
      //     </tr>
      //   </tbody>
      // </table>
      
      <div className='top-nav'>
        <div>
          <img className='logo' src={logo}/>
        </div>
        <div className='left'>POS</div>
        <div className='left'>WEB</div>
        <div className='left'>OMNI CHANNEL</div>
        <div className='left'>BẢNG GIÁ</div>
        <div className='left'>THÊM</div>
        <div className='right'>Trợ giúp</div>
        <div className='right'>Đăng nhập</div>
        <div className='right'>
            Dùng thử
        </div>
      </div>
    );
  }
}



class Homepage extends Component {
  render() {
    return (
     <Topbar/>
    );
  }
}

export default Homepage;
