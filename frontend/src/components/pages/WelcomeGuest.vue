<script setup>

</script>

<template>
    <section class="welcome">

        <div class="welcome-content-container">

            <div class="welcome-content has-text">
                <div class="welcome-text">
                    <h4>EN EĞLENCELİ BİLGİ YARIŞMASI PLATFORMU</h4>
                    <h2>Ekip Ruhunu Dijitalleştir.</h2>
                    <p>Çalışma hayatına heyecan katın. Projenizle, sadece bir quiz çözmekle kalmaz, aynı zamanda takım arkadaşlarınızla dostça bir rekabete girerek iş birliğinizi ve motivasyonunuzu artırırsınız.</p>
                </div>
                <div class="welcome-row">
                    <img src="/image.png" alt="" class="image">
                    <div class="start-buttons">
                        <button @click="$router.push('./auth')">Hemen Başlayın &rarr;</button>
                        <button @click="$router.push('./how-to-play')">Nasıl Oynanır? &rarr;</button>
                    </div>
                    <img src="/image.png" alt="" class="image">
                </div>
                
            </div>

            <div class="welcome-content">
                <div class="welcome-row">
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex veritatis similique cupiditate consequuntur non doloribus sequi esse accusamus inventore quasi eius, voluptatem natus assumenda asperiores quam? Nisi ipsam temporibus quod?</p>
                    <img src="/image.png" alt="" class="image">
                </div>
            </div>

            <div class="welcome-content">
                <div class="welcome-row">   
                    <img src="/image.png" alt="" class="image">
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex veritatis similique cupiditate consequuntur non doloribus sequi esse accusamus inventore quasi eius, voluptatem natus assumenda asperiores quam? Nisi ipsam temporibus quod?</p>
                </div>
            </div>
        </div>
        
    </section>
  
</template>

<style scoped>

    .welcome{
        display: flex;
        flex-direction: column;
        padding: 0;
        gap: 2rem;
    }

    .welcome-content,
    .welcome-content-container{
        display: flex;
        flex-direction: column;
    }

    .welcome-text{
        text-align: center;
        align-items: center;
        max-width: 700px;
        margin: 0 auto; 
    }

    .welcome-text p{
        width: auto;
    }

    .welcome-content{
        height: 75vh;
        box-sizing: border-box;
        justify-content: center;
        background-color: #ff6f61;
    }

    .welcome-content:not(.has-text) .welcome-row{
        gap: 2rem;
        justify-content: space-around;  
        align-items: center;
    }

    .welcome-content.has-text{
        justify-content: space-evenly;
    }

    .welcome-row{
        display: flex;
        text-align: center;
        flex-direction: row;
        gap: 1.5rem;
        justify-content: space-around;
        align-items: center;
    }

    .welcome-row p{
        max-width: 600px;
    }

    .start-buttons{
        display: grid;
        grid-template-rows: repeat(2, 1fr);
        gap: 1rem;
        margin-bottom: 1rem;
    }

    .start-buttons button{
        background-color: yellow;
        color: black;
        border: none;
        padding: 1rem 2rem;
        font-size: 1.2rem;
        cursor: pointer;
        border-radius: 8px;
        transition: background-color 0.3s ease;
    }

    .start-buttons button:hover{
        transform: scale(1.05);
        background-color: orange;
        transition: 0.3s ease;
    }

    .image{
        width: auto;
        height: auto;
        align-self: center;
    }
</style>