<template>
  <section class="d-multi-image-upload-preview z-draggable-preview">
    <!--<el-form-item-->
      <!--:label="option.label"-->
      <!--:prop="option.name"-->
      <!--:rules="getRules"-->
      <!--:required="option.isRequired"-->
    <!--&gt;-->
    <el-form-item
      :rules="getRules"
      :prop="option.name"
      :required="option.isRequired"
    >
    <div class="upload-item">
      <jee-multi-image-resource-upload
        v-bind="option"
        v-model="val"
        :isDescShow="false"
        :uploadOption="option"
        :isBan="isBan"
        :isBanText="isBanText"
        :banVal="banVal"
        :isBtn="false"
      ></jee-multi-image-resource-upload>
    </div>
    </el-form-item>
  </section>
</template>

<script>
import previewMixin from '@/components/draggable/Mixin/previewMixin'
export default {
  name: 'DMultiImageUploadPreview',
  mixins: [previewMixin],
  data () {
    return {
      isBan: [],
      isBanText: [],
      banVal: []
    }
  },
  computed: {
    getRules () {
      const { isRequired } = this.option
      let formItemRules = []
      if (isRequired) {
        formItemRules.push(this.$rules.requiredArray())
      }
      console.log(formItemRules)
      return formItemRules
    }
  },
  mounted () {
    this.replace(this.value)
    this.replaceImg(this.value)
    console.log(this.banVal)
  },
  methods: {
    replace (val) {
      // 文本类违禁词
      if (this.form.banJson && this.form.banJson[this.option.name] && this.form.banJson[this.option.name].txt) {
        let tempList = []
        this.form.banJson[this.option.name].txt.forEach(d => {
          if (d.indexOf(' ') !== -1) {
            let dList = d.split(' ')
            dList.forEach(i => {
              if (tempList.indexOf(i) === -1) {
                tempList.push(i)
              }
            })
          } else if (tempList.indexOf(d) === -1){
            tempList.push(d)
          }
        })
        tempList.forEach(d => {
          if (d && typeof d === 'string') {
            val.forEach((item, index) => {
              let txt = item.description
              if (txt.indexOf(d) !== -1) {
                let replaceTxt = '<span style="color: #fff; background-color: #FF0000;">' + d + '</span>'
                let key = new RegExp(d, 'g')
                let obj = {
                  index: index,
                  text: txt.replace(key, replaceTxt)
                }
                this.isBanText.push(index)
                this.banVal.push(obj)
              }
            })
          }
        })
      }
    },
    replaceImg (img) {
      // 图片类违禁词
      console.log(img)
      if (this.form.banJson && this.form.banJson[this.option.name] && this.form.banJson[this.option.name].img) {
        this.isBan = []
        this.form.banJson[this.option.name].img.forEach(d => {
          img.forEach((c, index) => {
            let url = c
            if (c instanceof Object) {
              url = c.resourcesSpaceData.url
            }
            if (d.indexOf(url) !== -1) {
              this.isBan.push(index)
            }
          })
        })
      }
    }
  }
}
</script>

<style lang="scss">
.d-multi-image-upload-preview.z-draggable-preview{
  >.el-form-item{
    padding-top: 2px;
  }
  .form-label{
    padding: 0 10px;
    display: inline;
    white-space:normal;
    word-break:break-all;
  }
  .upload-item{
    display: flex;
  }
}
</style>
