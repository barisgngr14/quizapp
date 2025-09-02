<script setup>
  import { ref } from 'vue'
  import axios from 'axios'
  import { useRoute } from 'vue-router'

  const email = ref('')
  const password = ref('')
  const confirmPassword = ref('')
  const name = ref('')
  const surname = ref('')
  const username = ref('')
  const route = useRoute()
  const activeTab = ref(route.params.tab || 'login')

  const login = async () => {
    try {
      const { data } = await axios.post('http://localhost:8080/api/login', {
        email: email.value,
        password: password.value
      })
      alert(data)
    } catch (error) {
      console.error('Login Hatası:', error)
      alert(error.response?.data || 'Sunucu hatası.')
    }
  }

  const signup = async () => {
    if (password.value !== confirmPassword.value) {
      alert('Şifreler uyuşmuyor!')
      return
    }

  try {
    const data = await axios.post('http://localhost:8080/api/signup', {
      name: name.value,
      surname: surname.value,
      username: username.value,
      email: email.value,
      password: password.value
    })
    alert('Kayıt başarılı. Artık giriş yapabilirsiniz.' + data)
  } catch (error) {
    console.error('Hata:', error)
    alert(error.response?.data || 'Sunucu hatası.')
  }
}
</script>

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

<style scoped>
  .container{
    height: 100vh;
    background-size: 100% 100%;
  }

  .auth-container {
    display:flex;
    justify-content: center;
    align-items: center;
    min-height: calc(100vh - 164px);
    background-color: #f3f0ea;
  }

  .auth-card {
    background-color: white;
    padding: 30px;
    border-radius: 15px; /* yuvarlama */
    box-shadow: 0 10px 25px black; /* kutu gölgesi */
    width: 100%;
    max-width: 500px; /* kutu eni */
    min-height: 400px; /* kutu boyu */
    text-align: center; /*yazılar ortada */
  }

  h2{
    margin-bottom: 30px; /*login-sig up yazısının alt metinlerden uzaklığı */
    font-size: 30px;  /*yazı büyüklüğü */
    color: #333 ;
  }

  .tabs{
    display: flex;
    background: #f1f1f1;
    border-radius: 10px; /* yuvarlama */
    overflow: hidden; /*kutunun dışına çıkan kısımlar görünmez olur. */
    margin-bottom: 20px; /* uzaklık */
  }

  .tabs button {
    flex: 1; /* butonların aynı boyutta görünmesi */
    padding: 10px ; /* login-sign up buton boyutu */
    border: none ; /*butonların çerçeveseini kaldırır */
    cursor: pointer ; /*butonlara geldiğinde el işareti çıkar */
    font-weight: bold;
    color: #333 ;
    background: transparent; /* butonların şeffaflığı */
    transition: 0.3s ease;
    border-radius: 10px;
  }

  button:hover {
    background: rgb(195, 240, 236);
  }

  .tabs button.active {
    background: linear-gradient(135deg, rgb(249, 201, 169), rgb(241, 202, 252));
    color: black;
    border-radius: 10px;
  }

  form {
    display: flex;
    flex-direction: column;
  }

  input {
    margin-bottom: 15px;
    border: 1px solid #ddd ;
    border-radius: 4px;
    outline: none ;
    background-color: white;
    color: #000;
    width: 93%;         /* Tam genişlik */
    padding: 12px 16px;  /* İç boşluk: dikey 12px, yatay 16px */
    font-size: 16px; 
  }

  input:focus {
    border-color:red ; /*seçilen kutucuğu gösteriyor */
  }

  .btn {
    background: linear-gradient(135deg, rgb(249, 201, 169), rgb(241, 202, 252));
    color: black;
    padding: 12px;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    font-weight: bold;
    transition: 0.3s ease;
  }

  .btn:hover {
    background: rgb(195, 240, 236);
    transform: scale(1.05);
  }

  .forgot {
    text-align: left;
    font-size: 14px;
    margin-bottom: 10px;
    color: #FA8072;
    text-decoration: none;
  }

  p {
    margin-top: 15px;
    font-size: 14px;
    margin-top:  6px;
  }

  p a {
    color: #FA8072;
    font-weight:bold;
    text-decoration: underline;
  }

</style>