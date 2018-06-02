import React, { Component } from 'react';
// import logo from './logo.svg';
import logo from './img/logo_3.png';
import './style.css';


class Topbar extends Component {
  render() {
    return (
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
          <img className='logo' src={logo} />
        </div>
        <div className='left'>POS</div>
        <div className='left'>WEB</div>
        <div className='left'>OMNI CHANNEL</div>
        <div className='left'>BẢNG GIÁ</div>
        <div className='left'>THÊM</div>
        <div className='right'>Trợ giúp</div>
        <div className='right'>Đăng nhập</div>
        <div className='right'>
          <span>Dùng thử</span>
        </div>
      </div>
    );
  }
}

class Topdescription extends Component {
  render() {
    return (
      <div className='desc'>
        Nền tảng quản lý và bán hàng đa kênh
        được sử dụng nhiều nhất Việt Nam
        <div></div>
        <p>
          +<strong>43,000</strong> doanh nghiệp và chủ shop đã chọn để bán hàng từ Online đến Offline
        </p>
      </div>
    );
  }
}

class Topbuttontrial extends Component {
  render() {
    return (
      <div className='button-trial-top'>
        <div className="button-trial-overlay"></div>
        <span>Dùng thử miễn phí</span>
      </div>
    );
  }
}
class Topbackground extends Component {
  render() {
    return (
      <div className='top-background'>
      </div>
    );
  }
}

class Buttondownscroll extends Component {
  render() {
    return (
      //scrolldown icon
      <div className="scrolldown-arrow">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512">
          <path d="M143 352.3L7 216.3c-9.4-9.4-9.4-24.6 0-33.9l22.6-22.6c9.4-9.4 24.6-9.4 33.9 0l96.4 96.4 96.4-96.4c9.4-9.4 24.6-9.4 33.9 0l22.6 22.6c9.4 9.4 9.4 24.6 0 33.9l-136 136c-9.2 9.4-24.4 9.4-33.8 0z" />
        </svg>
      </div>
    );
  }
}

class Middledescription extends Component {
  render() {
    return (
      <div className="mid-des">
        <div className="mid-des-1">
          BASIS - Giải pháp bạn cần để quản lý &amp; bán hàng tốt hơn
        </div>
        <div className="mid-des-2">
          Chúng tôi mang đến cho bạn một trợ lý ảo đắc lực để phát triển thương mại điện tử
        </div>
        <div className="mid-des-3">
          <div>
          </div>
          <div>
          </div>
          <div>
          </div>
        </div>
      </div>
    );
  }
}

class Homepage extends Component {
  render() {
    return (
      <div class="body">
        <Topbar />
        <Topdescription />
        <Topbuttontrial />
        <Topbackground />
        <Buttondownscroll />
        <Middledescription />
      </div>
    );
  }
}

export default Homepage;
