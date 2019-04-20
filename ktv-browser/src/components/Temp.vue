<template>
  
</template>

<script>
  export default {
    data() {
      return {
        restaurants: [],
        state1: '',
        state2: ''
      };
    },
    methods: {
      init() {
        document.onkeyup = e => {
          if (e.keyCode === 13)
            console.log(e)
        }
      }
    },
    created() {
      this.init()
    }
  }
</script>