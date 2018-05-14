import React, { Component } from 'react';
// import logo from './logo.svg';
import logo from './img/logo_3.png';
import './style.css';


class Topbar extends Component{
  render(){
    return(
      <table className='top-nav'>
        <tbody>
          <tr>
            <td>
              <img className='logo' src={logo} />
            </td>
            <td>POS</td>
            <td>WEB</td>
            <td>OMNI CHANNEL</td>
            <td>BẢNG GIÁ</td>
            <td>THÊM</td>
            <td>Trợ giúp</td>
            <td>Đăng nhập</td>
            <td>
              <button>
                Dùng thử
              </button>
            </td>
          </tr>
        </tbody>
      </table>
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
