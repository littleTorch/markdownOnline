<template>
  <div id="app">
    <el-container>
  <el-header id="headerShadow">

     <div id="nav">

        <div id="nav-left">
          <h2>Markdown</h2>
        </div>
        <div id="nav-right"> 
          
      
        <el-dropdown>
          <span class="el-dropdown-link userButton">
             <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
             <span id="userName1"></span>
          </span>
        <el-dropdown-menu slot="dropdown">
     <router-link to="/mainL/login"><el-dropdown-item>登录</el-dropdown-item></router-link>
    <router-link to="/Notes/newFile"><el-dropdown-item>笔记本</el-dropdown-item></router-link>
  </el-dropdown-menu>
</el-dropdown>



      </div>
     
    </div>

  </el-header>
  <el-main>
    <router-view/>

  </el-main>
</el-container>
   
    
  </div>
</template>

<script>
export default {
    // data(){
    //     return{
    //         name: $store.state.name,
    //     }
    // },
    methods:{

    },
    computed:{
        name(){
            return this.$store.state.name
        }
    }
};
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
     line-height: 60px;
}
#headerShadow{
  box-shadow: 3px 4px 6px rgb(118, 118, 118);

}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
* {
    padding: 0;
    margin: 0;
  }
  a{
      text-decoration: none;
  }

   .el-main {
    padding: 0!important;
  }
  #nav-left{
    float:left;
  }
  #nav-left h2{
  }
  #nav-right{
    float: right;
    height: 60px;
    line-height: 60px;
  }
.el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 15px;
  }
  .userButton{
    display: block;
    height: 60px;
    
  }
  .el-avatar{
    /* width: 50px!important;
    height: 50px!important;
    */
    margin-top: 10px;
    margin-right: 10px;
  }
#userName1{
  float: right;
}
</style>
