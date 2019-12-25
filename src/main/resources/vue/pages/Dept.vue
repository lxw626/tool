<template>
    <div>
        {{msg}}
        <Emp></Emp>
    </div>
</template>

<script>
    import Emp from './Emp.vue'

    export default {
        data() {
            return {
                msg: '我是dept'
            }
        },
        components: {
            Emp
        }
    }
</script>

<style>

</style>