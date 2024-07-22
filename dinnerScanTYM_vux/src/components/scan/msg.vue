<template>
  <div>
    <div class="msg">
      <msg :title="title" :description="description" :icon="icon"></msg>
    </div>
  </div>
</template>

<i18n>
Yeah! You make it:
  zh-CN: 提交成功
Msg description:
  zh-CN: 可前往访客记录查询登记状态
Primary button:
  zh-CN: 查看记录
Secondary button:
  zh-CN: 辅助操作
</i18n>

<script>
import { Msg, Divider, XButton } from "vux";
export default {
  components: {
    Msg,
    Divider,
    XButton,
  },
  created() {
    let self = this;
    let empNo = self.$route.params.empNo;
    self.getCurrentTime();
    self.title = "申报成功<br/>工号:" + empNo + "<br/>时间:" + self.updateTime;
  },
  methods: {
    changeIcon() {
      if (!this.icon || this.icon === "success") {
        this.icon = "warn";
        return;
      }
      if (this.icon === "warn") {
        this.icon = "info";
        return;
      }
      if (this.icon === "info") {
        this.icon = "waiting";
        return;
      }
      if (this.icon === "waiting") {
        this.icon = "success";
      }
    },
    getCurrentTime() {
      //获取当前时间并打印
      var _this = this;
      let yy = new Date().getFullYear();
      let mm = new Date().getMonth() + 1;
      let dd = new Date().getDate();
      let hh = new Date().getHours();
      let mf =
        new Date().getMinutes() < 10
          ? "0" + new Date().getMinutes()
          : new Date().getMinutes();
      let ss =
        new Date().getSeconds() < 10
          ? "0" + new Date().getSeconds()
          : new Date().getSeconds();
      this.updateTime = yy + "-" + mm + "-" + dd + " " + hh + ":" + mf + ":" + ss;
    },
  },
  data() {
    return {
      empNo: "",
      updateTime: "",
      title: "",
      description: "申报流程完成,点击左上角关闭按钮退出,谢谢您的配合!",
      icon: "",
      // buttons: [
      //   {
      //     type: "primary",
      //     text: "完成",
      //     onClick: this.step().bind(this)
      //   }
      // ]
    };
  },
};
</script>
