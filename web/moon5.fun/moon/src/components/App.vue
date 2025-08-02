<!-- App.vue -->
<template>
  <router-view />
  <!-- 添加爱心容器 -->
  <div id="heart-container"></div>
</template>

<script>
import '../assets/css/heart-animation.css';

export default {
  name: 'App',
  mounted() {
    document.addEventListener('click', this.createHeart);
  },
  beforeUnmount() {
    document.removeEventListener('click', this.createHeart);
  },
  methods: {
    createHeart(e) {
      const container = document.getElementById('heart-container');
      const heartCount = Math.floor(Math.random() * 3) + 3; // 3-5个爱心

      for (let i = 0; i < heartCount; i++) {
        const heart = document.createElement('i');
        heart.className = 'fa fa-heart heart';
        
        // 1. 生成烟花爆炸参数（随机方向和距离）
        const angle = Math.random() * Math.PI * 2; // 0-360° 随机角度（弧度）
        const burstRadius = Math.random() * 40 + 20; // 爆炸半径（20-60px）
        const burstX = Math.cos(angle) * burstRadius; // X方向爆炸偏移
        const burstY = Math.sin(angle) * burstRadius; // Y方向爆炸偏移

        // 2. 生成散落漂移参数
        const driftX = (Math.random() - 0.5) * 30; // 左右漂移（-15~15px）
        const rotate = (Math.random() - 0.5) * 180; // 旋转角度（-90~90°）
        const scale = 0.5 + Math.random() * 0.8; // 大小（0.5~1.3倍）

        // 3. 设置CSS变量（供动画使用）
        heart.style.setProperty('--burst-x', `${burstX}px`);
        heart.style.setProperty('--burst-y', `${burstY}px`);
        heart.style.setProperty('--drift-x', `${driftX}px`);
        heart.style.setProperty('--rotate', `${rotate}deg`);
        heart.style.setProperty('--scale', scale);
        
        // 4. 定位到鼠标点击位置（爆炸中心）
        heart.style.left = `${e.pageX}px`;
        heart.style.top = `${e.pageY}px`;
        heart.style.color = this.getRandomColor();
        heart.style.pointerEvents = 'none';
        
        container.appendChild(heart);

        // 动画结束后移除
        setTimeout(() => heart.remove(), 2000);
      }
    },
    getRandomColor() {
      const colors = ['#ff6b8b', '#ff8fa3', '#ffb6c1', '#ff4d6d', '#c70039'];
      return colors[Math.floor(Math.random() * colors.length)];
    }
  }
};
</script>

