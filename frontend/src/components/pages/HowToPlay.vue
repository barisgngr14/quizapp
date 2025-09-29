<script setup>

</script>

<template>
    <section class="howtoplay">

        <div class="howtoplay-content-container">

            <div class="howtoplay-content">
                <div class="howtoplay-row">
                    <p>"Quize Katıl" sekmesine geçtiğinizde karşınızda sağdaki şekilde görünen bir quiz listesi olacaktır. İstediğiniz quize ait katıl butonuna tıklayarak quizi başlatabilirsiniz.</p>
                    <img src="/enterquizpage.png" alt="" class="image">
                </div>
            </div>

            <div class="howtoplay-content">
                <div class="howtoplay-row">   
                    <img src="/image.png" alt="" class="image">
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex veritatis similique cupiditate consequuntur non doloribus sequi esse accusamus inventore quasi eius, voluptatem natus assumenda asperiores quam? Nisi ipsam temporibus quod?</p>
                </div>
            </div>

            <div class="howtoplay-content">
                <div class="howtoplay-row">
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex veritatis similique cupiditate consequuntur non doloribus sequi esse accusamus inventore quasi eius, voluptatem natus assumenda asperiores quam? Nisi ipsam temporibus quod?</p>
                    <img src="/image.png" alt="" class="image">
                </div>
            </div>

            <div class="howtoplay-content">
                <div class="howtoplay-row">   
                    <img src="/image.png" alt="" class="image">
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex veritatis similique cupiditate consequuntur non doloribus sequi esse accusamus inventore quasi eius, voluptatem natus assumenda asperiores quam? Nisi ipsam temporibus quod?</p>
                </div>
            </div>

            <div class="howtoplay-content">
                <div class="howtoplay-row">
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex veritatis similique cupiditate consequuntur non doloribus sequi esse accusamus inventore quasi eius, voluptatem natus assumenda asperiores quam? Nisi ipsam temporibus quod?</p>
                    <img src="/image.png" alt="" class="image">
                </div>
            </div>

            <div class="howtoplay-content">
                <div class="howtoplay-row">   
                    <img src="/image.png" alt="" class="image">
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex veritatis similique cupiditate consequuntur non doloribus sequi esse accusamus inventore quasi eius, voluptatem natus assumenda asperiores quam? Nisi ipsam temporibus quod?</p>
                </div>
            </div>

            <div class="howtoplay-content">
                <div class="howtoplay-row">
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex veritatis similique cupiditate consequuntur non doloribus sequi esse accusamus inventore quasi eius, voluptatem natus assumenda asperiores quam? Nisi ipsam temporibus quod?</p>
                    <img src="/image.png" alt="" class="image">
                </div>
            </div>

            <div class="howtoplay-content">
                <div class="howtoplay-row">   
                    <img src="/image.png" alt="" class="image">
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex veritatis similique cupiditate consequuntur non doloribus sequi esse accusamus inventore quasi eius, voluptatem natus assumenda asperiores quam? Nisi ipsam temporibus quod?</p>
                </div>
            </div>
        </div>
        
    </section>
  
</template>

<style scoped>

    .howtoplay{
        display: flex;
        flex-direction: column;
        padding: 0;
        gap: 2rem;
    }

    .howtoplay-content,
    .howtoplay-content-container{
        display: flex;
        flex-direction: column;
    }

    .howtoplay-content{
        height: 75vh;
        box-sizing: border-box;
        justify-content: center;
        background-color: #f3f0ea;
    }

    .howtoplay-row{
        display: flex;
        text-align: center;
        flex-direction: row;
        gap: 1.5rem;
        justify-content: space-around;
        align-items: center;
    }

    .howtoplay-row p{
        max-width: 600px;
    }

    .image{
        width: auto;
        height: auto;
        align-self: center;
    }

</style>