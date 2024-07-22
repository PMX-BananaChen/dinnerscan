<template>
  <div class="pmxScan">
    <br />
    <div class="center" style="color: dodgerblue">
      致伸东聚公司员工餐厅餐位申报
    </div>
    <br />
    <div class="center1" style="color: #ff0000">
      为了大家的安全健康,请在各餐厅用餐期间扫描
    </div>
    <div class="center1" style="color: #ff0000">
      餐桌上的二维码进行餐位申报,谢谢大家配合!
    </div>
    <br />
    <div style="padding: 15px">
      <x-button @click.native="scan" type="primary" v-if="returnCode == '2'"
        >二维码无效,请重新扫码</x-button
      >
      <x-button @click.native="scan" type="primary" v-else>扫一扫</x-button>
    </div>
    <br />
    <div class="center1">点击上方"扫一扫"按钮扫描识别</div>
    <div class="center1">餐桌上二维码即可完成申报</div>
  </div>
</template>
<script>
import { Confirm, XButton, XInput, Toast } from "vux";
//判断字符是否非空
function isNotEmpty(str) {
  if (str != null && str.length > 0) {
    return true;
  }
  return false;
}

function getUrlParam(name) {
  //封装方法
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
  var r = window.location.search.substr(1).match(reg); //匹配目标参数
  if (r != null) return unescape(r[2]);
  return null; //返回参数值
}
import { mapGetters } from "vuex";
// import axios from "axios";
import qs from "qs";
export default {
  inject: ["reload"],
  computed: {
    ...mapGetters(["empNo"]),
  },
  mounted() {
    this.$store.commit("UPDATE_PAGE_TITLE", "挂号排队");
  },
  data() {
    return {
      company: "PMX",
      userId: "",
      qrCode: "",
      empNo: "",
      returnCode: "",
      msg: "",
    };
  },
  created() {
    const code = getUrlParam("code"); // 截取路径中的code，如果没有就去微信授权，如果已经获取到了就直接传code给后台获取openId
    if (code) {
      // 通过code获取 openId等用户信息
      this.$axios
        .get("OAuth2/authorize1?code=" + code)
        .then((res) => {
          let datas = res.data;
          if (datas.code === 1) {
            console.log("授权成功");
            this.$store.commit("UPDATE_USERID", datas.data);
            this.userId = datas.data;
            if (this.userId != null) {
              this.$axios
                .get("dinnerScan/getPmxEmployeeNo?userId=" + datas.data)
                .then((res) => {
                  let datas = res.data;
                  if (datas.code === 1) {
                    this.empNo = datas.data;
                    this.$store.commit("UPDATE_EMPNO", datas.data);
                    //去除code
                    // window.history.replaceState(null, null, 'http://wx2.primax.com.cn/dinnerScan_test')
                  }
                })
                .catch((error) => {
                  console.log(error);
                });
            } else {
              this.getCodeApi("dinnerScan");
            }
          }
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      this.getCodeApi("dinnerScan");
    }
    //调用js-sdk签名
    this.getgetTicketSign();
  },
  methods: {
    getCodeApi(state) {
      //获取code
      let urlNow = encodeURIComponent('https://wx2.primax.com.cn/dinnerScan');
      let scope = "snsapi_base"; //snsapi_userinfo   //静默授权 用户无感知
      let appid = "ww24261f29f0d4837c";
      let url = `https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${urlNow}&response_type=code&scope=${scope}&state=${state}#wechat_redirect`;
      window.location.replace(url);
    },
    getgetTicketSign() {
      let url = encodeURIComponent(location.href.split("#")[0]);
      // 通过code获取 openId等用户信息
      this.$axios
        .get("OAuth2/getTicketSignByPmx?url=" + url)
        .then((res) => {
          let datas = res.data;
          if (datas.code === 1) {
            wx.config({
              debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来
              appId: "ww24261f29f0d4837c", // 必填，公众号的唯一标识
              timestamp: datas.data.timestamp, // 必填，生成签名的时间戳
              nonceStr: datas.data.nonceStr, // 必填，生成签名的随机串
              signature: datas.data.signature, // 必填，签名，见附录1
              jsApiList: ["scanQRCode"], // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
            });
            wx.ready(() => {
              console.log("微信js-sdk 配置成功");
            });
            wx.error(function (res) {
              console.log("微信js-sdk配置失败");
            });
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    scan() {
      let _this = this;
      wx.scanQRCode({
        needResult: 1,
        scanType: ["qrCode", "barCode"],
        success(res) {
          _this.qrCode = res.resultStr;
          if (_this.empNo === null || _this.empNo === "") {
            _this.$router.push({
              path: "addEmpNo",
              query: {
                company: _this.company,
                wxUserId: _this.userId,
                qrCode: _this.qrCode,
              },
            });
          } else {
            let scanInfo = {
              company: _this.company,
              wxUserId: _this.userId,
              empNo: _this.empNo,
              qrCode: _this.qrCode.trim(),
            };
            _this.$axios
              .post(`dinnerScan/addDinnerScanInfo`, JSON.stringify(scanInfo), {
                headers: { "Content-Type": "application/json; charset=UTF-8" },
              })
              .then((res) => {
                let datas = res.data;
                if (datas.code === 1) {
                  _this.$router.push({
                    name: "msg",
                    params: { empNo: _this.empNo },
                  });
                } else if (datas.code === 2) {
                  _this.returnCode = datas.code;
                  _this.msg = datas.msg;
                  // this.$vux.toast.text(datas.msg,'middle');
                } else if (datas.code === 3) {
                  this.$vux.toast.text(datas.msg, "middle");
                }
              })
              .catch((error) => {
                console.log(error);
              });
          }
        },
      });
    },
  },
  components: {
    Confirm,
    XButton,
    XInput,
    Toast,
  },
};
</script>
<style>
.center {
  text-align: center;
  font-size: 22px;
}
.center1 {
  text-align: center;
  font-size: 16px;
}
.red {
  color: red;
}
</style>