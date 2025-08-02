<template>
  <div class="inputForm">
    <i class="fa fa-lock"></i>
    <!-- 动态绑定输入框类型 -->
    <input 
      :type="showPassword ? 'text' : 'password'" 
      class="input" 
      :placeholder="placeholder" 
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
    >
    <!-- 动态绑定图标 + 切换状态 -->
    <i 
      :class="showPassword ? 'fa fa-eye' : 'fa fa-eye-slash'" 
      @click="showPassword = !showPassword" 
      style="cursor: pointer;"
    ></i>
  </div>
</template>

<script>
export default {
  name: 'PasswordInput',
  props: {
    placeholder: {
      type: String,
      default: 'Enter Password'
    },
    modelValue: {  // 接收父组件 v-model 的值
      type: String,
      required: true
    }
  },
  emits: ['update:modelValue'],  // 声明要触发的事件
  data() {
    return {
      showPassword: false,
      localValue: this.modelValue  // 本地数据同步 prop 初始值
    }
  },
  watch: {
    // 监听本地数据变化，通知父组件更新
    localValue(newVal) {
      this.$emit('update:modelValue', newVal);
    },
    // 监听 prop 变化，同步到本地数据
    modelValue(newVal) {
      this.localValue = newVal;
    }
  }
}
</script>