<template>
  <div class="dinnerTH">
    <br />
    <div class="center" style="color:dodgerblue;">แจ้งกลุ่มพนักงาน ที่นั้งรับประทานอาหาร</div>
    <div class="center" style="color:dodgerblue;">致伸集团员工餐厅餐位申报</div>
    <br />
    <group title=" ">
      <x-input
        v-model="empNo"
        title="รหัสพนักงาน"
        :inline-desc="titl"
        type="number"
        placeholder="กรอกรหัสพนักงาน"
        placeholder-align="right"
        text-align="right"
        required
      ></x-input>
    </group>

    <div style="padding:20px;">
      <x-button type="primary" v-if="empNo ===''||empNo === null " disabled>ส่ง(提交申报)</x-button>
      <x-button @click.native="showPlugin" type="primary" v-else>ส่ง(提交申报)</x-button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Msg, Divider, XButton, Group, XInput, Toast } from "vux";
export default {
  components: {
    Msg,
    Divider,
    XButton,
    Group,
    XInput,
    Toast
  },
  data() {
    return {
      company: "TH",
      qrCode: "",
      empNo: "",
      titl: "工号"
    };
  },
  created() {
    this.getParams();
  },
  methods: {
    getParams() {
      // 取到url带过来的参数
      const qrCode = this.$utils.getUrlKey("qrCode");
      this.qrCode = qrCode;
      console.log("this.qrCode====" + this.qrCode);
      console.log("this.qrCode====" + this.qrCode);
      console.log("this.qrCode====" + this.qrCode);
    },
    showPlugin() {
      let _this = this;
      //员工ID
      let scanInfo = {
        company: _this.company,
        empNo: _this.empNo,
        qrCode: _this.qrCode
      };
      console.log(scanInfo);
      axios
        .post(`dinnerScan/addDinnerScanInfoByTHA`, JSON.stringify(scanInfo), {
          headers: { "Content-Type": "application/json; charset=UTF-8" }
        })
        .then(res => {
          let datas = res.data;
          if (datas.code === 1) {
            _this.$router.push({
              path: "msg",
              query: {
                qrCode: _this.qrCode
              }
            });
          } else if (datas.code === 2) {
            this.$vux.toast.text(datas.msg.replace(/\n/g, "<br>"), "middle");
          }
        })
        .catch(error => {
          console.log(error);
        });
    }
  },
  watch: {
    $route: "getParams"
  }
};
</script>
<style>
.center {
  text-align: center;
  font-size: 22px;
}
.text-wrapper {
  white-space: pre-wrap;
}
</style>