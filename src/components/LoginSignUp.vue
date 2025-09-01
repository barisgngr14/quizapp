<template>
        <div class="auth-container">
       <div class="auth-card">
        <h2>{{ activeTab === 'login' ? 'Login' : 'Create Account' }}</h2>
  
        <div class="tabs">
          <button 
            :class="{ active: activeTab === 'login' }" 
            @click="activeTab = 'login'">Login</button>
          <button 
            :class="{ active: activeTab === 'signup' }" 
            @click="activeTab = 'signup'">Sign Up</button>
        </div>
        <form v-if="activeTab === 'login'" @submit.prevent="login">
          <input type="email" v-model="email" placeholder="Email Address" required>
          <input type="password" v-model="password" placeholder="Password" required>
          <a href="#" class="forgot">Forgot Password ?</a>
          <button type="submit" class="btn">Login</button>
          <p>Not a member?<a @click.prevent="activeTab= 'signup'" href="#"> Sign Up Now</a></p>
        </form>

        <form v-else @submit.prevent="signup">
            <input type="name" v-model="name" placeholder="Name" required>
            <input type="surname" v-model="surname" placeholder="Surname"required>
            <input type="username" v-model="username" placeholder="Username"required>
            <input type="email" v-model="email" placeholder="Email"required>
            <input type="password" v-model="password" placeholder="Password"required>
            <input type="password" v-model="confirmPassword" placeholder="Confirm Password" required>
            <button type="submit" class="btn">Sign Up</button>
            <p>Already have an account?<a @click.prevent="activeTab = 'login'" href="#"> Login</a></p>        
        </form>
        </div>
    </div>
</template>

<script>
export default {
    name: "B",
    data() {
        return {
            activeTab: "login",
        };
    },
    methods: {
        async login() {
            try {
                const response = await fetch("http://localhost:8080/api/register", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        email: this.email,
                        password: this.password,

                    })
                });
                const result = await response.text();
                if (response.ok) {
                    alert(result);
                } else {
                    alert(result);
                }
            } catch (error) {
                console.error("Login Hatası:", error);
                alert("Sunucuya Bağlanılamadı.");
            }
        },
        signup() {
  if (this.password !== this.confirmPassword) {
    alert("Şifreler uyuşmuyor!");
    return;
  }

  fetch("http://localhost:8080/api/LoginSignUp", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      name: this.name,
      surname: this.surname,
      username: this.username,
      email: this.email,
      password: this.password
    })
  })
  .then(response => {
    if (response.ok) {
      return response.text();
    } else {
      throw new Error("Kayıt başarısız");
    }
  })
  .then(data => {
    alert("Sunucudan gelen yanıt: " + data);
  })
  .catch(error => {
    console.error("Hata:", error);
    alert("Sunucuya bağlanılamadı.");
  });
}
    }
}

</script>