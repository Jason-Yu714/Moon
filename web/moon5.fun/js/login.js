document.addEventListener('DOMContentLoaded', function() {
    // 获取元素
    const username = document.querySelector('input[name="username"]');
    const password = document.querySelector('input[name="password"]');
    const submitBtn = document.querySelector('.button-submit');

    // 检查元素是否成功获取
    console.log('Username input:', username);
    console.log('Password input:', password);
    console.log('Sign In button:', submitBtn);

    if (submitBtn && username && password) {
        // 添加点击事件
        submitBtn.addEventListener('click', function (event) {
            event.preventDefault(); // 阻止表单默认提交行为
            // 获取用户输入的值
            const usernameVal = username.value;
            const passwordVal = password.value;
            if (usernameVal === 'admin' && passwordVal === '123456') {
                console.log('登录成功');
            } else {
                console.log('登录失败，清空密码');
                password.value = '';
            }
        });
    } else {
        console.log('某些元素未成功获取，请检查 HTML 结构。');
    }

    // 节流函数
    function throttle(func, delay) {
        let timer = null;
        return function() {
            if (!timer) {
                func.apply(this, arguments);
                timer = setTimeout(() => {
                    timer = null;
                }, delay);
            }
        };
    }


    // 添加鼠标点击事件
    document.addEventListener("click", function(event) {
        // 每次点击生成多个花瓣
        for (let i = 0; i < 5; i++) {
            createPetal(event.clientX, event.clientY);
        }
    });

    function createPetal(x, y) {
        const tail = document.createElement("div");
        tail.classList.add("tail");

        // 随机设置花瓣大小
        const size = Math.random() * 20 + 10; // 10px 到 30px 之间
        tail.style.width = `${size}px`;
        tail.style.height = `${size}px`;

        // 调整炸开的距离，范围为 50px 到 200px 之间
        const explodeDistance = Math.random() * 150 + 50; 
        const angle = Math.random() * 2 * Math.PI;
        const explodeX = explodeDistance * Math.cos(angle);
        const explodeY = explodeDistance * Math.sin(angle);

        // 设置 CSS 变量
        tail.style.setProperty('--explode-x', explodeX);
        tail.style.setProperty('--explode-y', explodeY);

        // 设置初始位置
        tail.style.left = x + "px";
        tail.style.top = y + "px";

        // 随机设置初始旋转角度
        const rotate = Math.random() * 360;
        tail.style.transform = `rotate(${rotate}deg)`;

        // 调整动画持续时间，范围为 2s 到 4s 之间
        const duration = Math.random() * 2 + 2; 
        tail.style.animationDuration = `${duration}s`;

        document.body.appendChild(tail);

        // 动画结束后移除元素
        tail.addEventListener('animationend', () => {
            tail.remove();
        });
    }
});