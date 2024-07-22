<template>
  <div class="dinnerTHHome" style="height: 100%">
    <view-box ref="viewbox" body-padding-top="46px">
     
      <div v-transfer-dom class="loading-box">
        <loading :show="loading" position="absolute"></loading>
      </div>
      <router-view :style="routerViewClass"></router-view>
      
    </view-box>
  </div>
</template>

<script>
import {
  Loading,
  ViewBox,
  LoadMore
} from "vux";
import { TransferDomDirective as TransferDom } from "vux";


export default {
  
  data() {
    return {
      routerViewClass: {
        height: "",
        overflow: "scroll"
      }
    };
  },
  methods: {
    setHeight() {
      this.routerViewClass.height = window.innerHeight - 96 + "px";
      // console.log(this.routerViewClass);
    }
  },
  mounted() {
    this.setHeight()
  },
  directives: {
    TransferDom
  },
  components: {
    Loading,
    ViewBox,
    LoadMore
  }
};
</script>

<style>
.memberHome .vux-demo-header-box {
  z-index: 100;
  position: absolute;
  width: 100%;
  left: 0;
  top: 0;
}

.memberHome .weui-tabbar__icon img {
  width: auto;
  height: 90%;
}

.memberHome .weui-tabbar__item.weui-bar__item_on .weui-tabbar__label {
  color: #dd5858;
}
</style>