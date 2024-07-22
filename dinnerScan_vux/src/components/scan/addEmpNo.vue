<template>
  <div class="pmxScan">
    <br />
    <div class="center" style="color:dodgerblue;">致伸东聚公司员工餐厅餐位申报</div>
    <br />
    <group title="请输入工号">
      <x-input
        v-model="empNo"
        title="工号"
        placeholder-align="right"
        text-align="right"
        required
      ></x-input>
      </group>
     
      <div style="padding:20px;">
      <x-button  type="primary" v-if="empNo ===''||empNo === null " disabled>提交申报</x-button>
      <x-button @click.native="showPlugin" type="primary" v-else >提交申报</x-button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Msg, Divider, XButton ,Group,XInput} from "vux";
export default {
  components: {
    Msg,
    Divider,
    XButton,
    Group,
    XInput
  },
  data() {
    return {
      company: "",
      wxUserId: "",
      qrCode: "",
      empNo: ""
    };
  },
  created() {
    this.getParams();
  },
  methods: {
    getParams() {
      // 取到路由带过来的参数
      const company = this.$route.query.company;
      this.company = company;

      const wxUserId = this.$route.query.wxUserId;
      this.wxUserId = wxUserId;

      const qrCode = this.$route.query.qrCode;
      this.qrCode = qrCode;
    },
    showPlugin() {
       let _this = this;
      //员工ID
      let scanInfo = {
        company: _this.company,
        wxUserId: _this.wxUserId,
        empNo: _this.empNo,
        qrCode: _this.qrCode.trim()
      };
      console.log(scanInfo);
      axios
        .post(`dinnerScan/addDinnerScanInfo`, JSON.stringify(scanInfo), {
          headers: { "Content-Type": "application/json; charset=UTF-8" }
        })
        .then(res => {
          let datas = res.data;
          if (datas.code === 1) {
            console.log(datas.msg);
            _this.$router.push({ name: 'msg', params: { empNo: _this.empNo} });
          } else if (datas.code === 2) {
            this.$vux.toast.text(datas.msg, "middle");
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
</style>