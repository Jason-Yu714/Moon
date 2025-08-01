// 错误示例（含无效字符）：
// document.querySelector('.fa-eye#slash'); // 类名中含 '#' 导致无效

// 正确示例（仅使用字母、数字、连字符和空格）：
export function togglePasswordVisibility(vm) {
  vm.showPassword = !vm.showPassword; // 直接操作组件状态，避免 DOM 选择器错误
}