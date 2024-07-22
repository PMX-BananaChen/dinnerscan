<template>
  <div>
    <div class="msg">
      <msg :title="title" :description="description2" :icon="icon" class="text-wrapper" v-if="qrCode == 'B10000'"></msg>
      <msg :title="title" :description="description" :icon="icon" class="text-wrapper" v-else></msg>
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
    XButton
  },
  created() {
    this.getParams();
  },
  methods: {
    getParams() {
      // 取到路由带过来的参数
      const qrCode = this.$route.query.qrCode;
      this.qrCode = qrCode;
    },
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
    }
  },
  data() {
    return {
      qrCode: "",
      title: "ลงทะเบียนสำเร็จแล้ว\n申报成功",
      description:
        "ขั้นตอนการลงทะเบียนสำเร็จแล้ว ขอบคุณที่ให้ความร่วมมือ\n申报流程完成,谢谢您的配合",
      description2:
        "ออกไปทานข้าวนอกบ้านเสร็จสิ้นแล้วขอขอบคุณสำหรับความร่วมมือของคุณ\n外出就餐申报完成,谢谢您的配合",
      icon: ""
      // buttons: [
      //   {
      //     type: "primary",
      //     text: "完成",
      //     onClick: this.step().bind(this)
      //   }
      // ]
    };
  }
};
</script>
<style>
.text-wrapper {
  white-space: pre-wrap;
}
</style>