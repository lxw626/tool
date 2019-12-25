<template>
  <div>
    父组件:{{msg}}
    <Dept></Dept>
  </div>
</template>

<script>
    import Dept from './Dept'

    export default {
        data() {
            return {
                msg: "我是app"
            }
        },
        components: {
            Dept
        }
    }
</script>

<style>

</style>