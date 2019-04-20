<template>
  <el-container style="border: 1px solid #eee">

    <!-- 侧边栏 -->
    <transition name="el-zoom-in-center">
      <el-aside width="20%" v-if="asideShow" style="height: 57em" align="left">
        <el-menu :default-openeds="['1', '2']">

          <!-- 我的收藏 -->
          <el-submenu index="1" v-show="username != 'admin'">
            <template slot="title"><i class="el-icon-star-on"></i>我的收藏</template>
            <el-menu-item-group v-for="music in favoriteMusics" :key="music.songId">
              <el-menu-item :id="'1-'+music.songId" :index="'1-'+music.songId">
                {{ music.songName }}
                <i class="el-icon-caret-right" style="position: absolute; right: 2em; top:0.8em" @click="initPlayMusics(music.songId, true)"/>
                <i class="el-icon-delete" style="position: absolute; right: 0em; top:0.8em" @click="manageFavorite(music, false)"/>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <!-- 正在播放 -->
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-service"></i>正在播放</template>
            <el-menu-item-group v-for="music in playingMusics" :key="music.songId">
              <el-menu-item :index="'2-'+music.songId">
                {{ music.songName }}
                <i :id="'2-'+music.songId" class="el-icon-caret-right" style="position: absolute; right: 2em; top:0.8em" @click="play(music.songId)"/>
                <i class="el-icon-star-off" style="position: absolute; right: 0em; top:0.8em" @click="manageFavorite(music, true)"/>
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          
        </el-menu>
      </el-aside>
    </transition>

    <!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

    <el-container>

      <!-- 标题栏 -->
      <el-header style="height: 4em">
        <!-- 搜索 -->
        <div align="left">&emsp;&emsp;
          <el-input placeholder="搜索歌曲" v-model="condition.value" style="width: 60%" align="left">
            <el-select v-model="condition.type" slot="prepend" placeholder="歌曲名称" style="width: 8em">
              <el-option label="歌曲名称" value="1"></el-option>
              <el-option label="歌名缩写" value="2"></el-option>
              <el-option label="歌手名称" value="3"></el-option>
            </el-select>
            <el-button slot="append" icon="el-icon-search" @click="initSearch()"></el-button>
          </el-input>&emsp;&emsp;
          <span v-show="username != 'admin'">
            <el-switch
              v-model="musicView"
              active-text="全部音乐"
              inactive-text="收藏榜单">
            </el-switch>
          </span>
          <span style="position:absolute;right:2em">
            <!-- 功能 -->
            欢迎您，
            <el-dropdown @command="dropdownCommand">
              <el-button type="text" style="font-size: 1.5em; margin-right: 15px">{{ username }}</el-button>
              <el-dropdown-menu slot="dropdown" >
                <el-dropdown-item command="侧边栏开关">侧边栏开关</el-dropdown-item>
                <!-- <el-dropdown-item v-if="username=='admin'" command="添加歌曲">添加歌曲</el-dropdown-item> -->
                <el-dropdown-item command="退出登录">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </span>
        </div>
      </el-header>
    
      <!-- 音乐表单 -->
      <el-main style="height: 48em">
        <div v-if="musicView">
          <el-table :data="musics">
            <el-table-column prop="songName" label="音乐标题" width="400" />
            <el-table-column prop="singerName" label="歌手" width="400" />
            <el-table-column prop="duration" label="时长" width="200" />
            <el-table-column prop="songLanguage" label="语种" />
            <el-table-column fixed="right" label="操作" width="300" align="center">
              <template slot-scope="scope">
                <el-button type="text" size="small" v-if="username == 'admin'" @click="manageSong(scope.row)">管理</el-button>
                <el-button type="text" size="small" @click="initPlayMusics(scope.row.songId, false)">播放</el-button>
                <el-button type="text" size="small" @click="manageFavorite(scope.row, true)">收藏</el-button>
                <el-button type="text" size="small" @click="download(scope.row)">下载</el-button>
              </template>
            </el-table-column>
          </el-table><br>
          <el-pagination @current-change="handleCurrentChange" :current-page="currentPage" :page-size="11" layout="total, prev, pager, next, jumper"
            :total="totalPage">
          </el-pagination>
        </div>
        <div v-else>
          <el-table :data="recommendMusics">
            <el-table-column prop="songName" label="音乐标题" width="400" />
            <el-table-column prop="singerName" label="歌手" width="400" />
            <el-table-column prop="duration" label="时长" width="200" />
            <el-table-column prop="numOfCollection" label="热度" />
            <el-table-column fixed="right" label="操作" width="300" align="center">
              <template slot-scope="scope">
                <el-button type="text" size="small" v-if="username == 'admin'" @click="manageSong(scope.row)">管理</el-button>
                <el-button type="text" size="small" @click="initPlayMusics(scope.row.songId, false)">播放</el-button>
                <el-button type="text" size="small" @click="manageFavorite(scope.row, true)">收藏</el-button>
                <el-button type="text" size="small" @click="download(scope.row)">下载</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-main>
      <!-- 播放器 -->
      <el-footer style="height: 5em"><br>
        <audio id="audio" :src="musicUrl" style="width: 100%" @ended="playNext()" controls />
      </el-footer>
    </el-container>

    <!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
    
    <!-- 管理窗口：音乐 -->
    <el-dialog title="音乐管理" :visible.sync="dialogTableVisible" width="40em">

      <el-input v-model="music.songName" class="normal-input" placeholder="请输入音乐标题" maxlength="30" clearable>
        <template slot="prepend">音乐标题</template>
      </el-input><br><br>
      <el-input v-model="music.songNameAbbr" class="small-input" placeholder="请输入标题缩写" maxlength="30" clearable>
        <template slot="prepend">标题缩写</template>
      </el-input>&emsp;&emsp;
      <el-input v-model="music.songLanguage" class="small-input" placeholder="请输入语种" maxlength="30" clearable>
        <template slot="prepend">&emsp;语种&emsp;</template>
      </el-input><br><br>
      
      <!--  class="inline-input" -->
      <!-- <el-autocomplete v-model="singer.singerName" :fetch-suggestions="getSingers" @select="lockSingerInfo" :disabled="inputDisable"
                        class="small-input" placeholder="请输入歌手姓名">
        <template slot="prepend">歌手姓名</template>
      </el-autocomplete>&emsp;&emsp; -->

      <el-input v-model="singer.singerName" :disabled="inputDisable"
                class="small-input" placeholder="请输入歌手姓名" maxlength="30" clearable>
        <template slot="prepend">&emsp;姓名&emsp;</template>
      </el-input>&emsp;&emsp;

      <el-input v-model="singer.singerSex" :disabled="inputDisable"
                class="small-input" placeholder="请输入歌手性别" maxlength="30" clearable>
        <template slot="prepend">&emsp;性别&emsp;</template>
      </el-input><br><br>
      <el-input v-model="singer.singerNationality" :disabled="inputDisable"  placeholder="请输入歌手国籍"
                class="small-input" maxlength="30" clearable>
        <template slot="prepend">歌手国籍</template>
      </el-input>&emsp;&emsp;
      <el-input v-model="singer.singerRegion" :disabled="inputDisable" placeholder="歌手所在地区"
                class="small-input" maxlength="30" clearable>
        <template slot="prepend">所在地区</template>
      </el-input>
      <br><br><br>

      <div>
        <div v-if="newMusic" align="right">
          <el-button type="primary" @click="insertMusic()" round>添加</el-button>&emsp;
        </div>
        <div v-else>
          <el-button type="primary" @click="alterMusic()" round>修改</el-button>&emsp;&emsp;
          <el-button type="danger" round>删除</el-button>
          <el-button type="text" style="position: absolute; right: 4em" @click="manageSinger()">管理该歌手信息</el-button>
        </div>
      </div>
      
      <!-- 管理窗口：歌手 -->
      <el-dialog title="歌手信息" :visible.sync="dialogTableVisible2" width="20em" append-to-body>
        <el-input  v-model="singer2.singerName" class="small-input" placeholder="请输入歌手姓名" clearable>
          <template slot="prepend">歌手姓名</template>
        </el-input>&emsp;&emsp;
        <!-- <el-input v-model="singer2.singerSex"
                  class="small-input" placeholder="请输入歌手性别" maxlength="30" clearable>
          <template slot="prepend">&emsp;性别&emsp;</template>
        </el-input><br><br> -->
        <el-input v-model="singer2.singerNationality"
                  class="small-input" placeholder="歌手所在国籍" maxlength="30" clearable>
          <template slot="prepend">歌手国籍</template>
        </el-input>&emsp;&emsp;
        <el-input v-model="singer2.singerRegion"
                  class="small-input" placeholder="歌手所在地区" maxlength="30" clearable>
          <template slot="prepend">所属地区</template>
        </el-input><br><br>
        
        <el-tag type="info">性别</el-tag>&emsp; 
        <el-switch
          v-model="singer2.singerSex"
          active-text="男"
          inactive-text="女">
        </el-switch>&emsp;&emsp;&emsp;
        <el-button type="primary" @click="alterSinger()" round>修改</el-button>
        <!-- <div align="right"><el-button type="primary" @click="alterSinger()" round>修改</el-button>&emsp;</div> -->
      </el-dialog>

    </el-dialog>


  </el-container>


</template>

<style>
  .el-header {
    color: #333;
    line-height: 60px;
    background-color: #e8ece7; 
    text-align: right;
  }
  
  .el-aside {
    color: #333;
    background-color: rgb(238, 241, 246)
  }

  .normal-input {
    width: 40em
  }

  .small-input {
    width: 19em
  }
</style>

<script>
  export default {
    data() {
      const item = {
        songName: '老鼠爱大米',
        singerName: 'er_dong_chen',
        songLanguage: '汉语',
        songNameAbbr: 'LSADM',
        album: '河东狮',
        duration: '11:02'
      };
      return {
        asideShow: true,  // 侧边栏可视化
        dialogTableVisible: false,  // 音乐管理
        dialogTableVisible2: false, // 歌手管理
        inputDisable: false, // 音乐管理专用
        newMusic: false, // 添加歌曲

        username: '',
        musicUrl: '',
        musicUrlPrefix : '/music/play.do',

        favoriteMusics: [],
        playingMusics: [],
        currentPlayingMusicId: 0,

        condition: {
          value: ''
        },
        currentPage: 1,
        totalPage: 0,
        showAll: true,

        musicView: true,

        singerName: '',
        singer: {},   // 音乐管理
        singer2: {},  // 歌手管理
        singers: [],
        music: {},
        recommendMusics: [],
        musics: Array(20).fill(item),
      }
    },
    methods: {

      // ----------------------------------------------- 我的收藏

      // 获取收藏列表
      getFavoriteMusics() {
        this.post('/music/getFavorites.do', {}, (response) => {
          this.favoriteMusics = response.favoriteMusics
        })
      },

      // 管理我的收藏
      manageFavorite(music, add) {
        if (this.username == 'admin') {
          console.warn('跳过“收藏”')
          return;
        }
        if (this.favoriteMusics.filter((favorite) => {
          return favorite.songId == music.songId
        }).length > 0)
          return;
        var form = {
          add: add,
          songId: music.songId
        }
        this.post('/music/manageFavorite.do', form, () => {
          if (add) {
            this.$message.success('添加成功')
            this.favoriteMusics.push({
              songId: music.songId,
              songName: music.songName
            })
            console.log(this.favoriteMusics)
          } else {
            this.favoriteMusics = this.favoriteMusics.filter((favorite) => {
              return favorite.songId != music.songId
            })
          }
        })
      },

      // ----------------------------------------------- 正在播放

      // favorite 表示收藏的音乐或是列表的音乐
      initPlayMusics(songId, favorite) {
        // 初始化播放列表
        this.playingMusics = favorite ? this.favoriteMusics : this.musics
        this.currentPlayingMusicId = songId
        setTimeout(() => {
          document.getElementById('2-' + songId).click()
        }, 200);
      },

      // 播放音乐，item 为音乐信息
      play(songId) {
        // 播放音乐
        this.currentPlayingMusicId = songId
        this.musicUrl = this.musicUrlPrefix + '?songId=' + songId
        console.log(this.musicUrl)
        setTimeout(() => {
          document.getElementById('audio').play()
        }, 200);
      },

      // 播放器结束之后，播放下一首
      playNext() {
        if (this.playingMusics.length == 0)
          return;
        
        for (var i = 0; i < this.playingMusics.length; i++) {
          if (this.currentPlayingMusicId == this.playingMusics[i].songId) {
            if (i + 1 == this.playingMusics.length)
              break;
            this.currentPlayingMusicId = this.playingMusics[i+1].songId
            setTimeout(() => {
              document.getElementById('2-' + this.currentPlayingMusicId).click()
            }, 200);
            break;
          }
        }
      },

      // ----------------------------------------------- 音乐列表

      // 搜索
      initSearch() {
        this.musicView = true
        this.showAll = false
        this.pageIndex = 1
        this.search(this.pageIndex)
      },
      search(pageIndex) {
        var form = {
          type: this.condition.type ? this.condition.type : 1,
          value : this.condition.value,
          pageIndex : pageIndex ? pageIndex : 1
        }
        this.post('/music/search.do', form, (response) => {
          this.musics = response.musics
          this.totalPage = response.totalPage
        })
      },

      // 获取音乐列表
      getMusics(pageIndex) {
        this.musicView = true
        this.showAll = true
        this.post('/music/getMusicList.do', { pageIndex: pageIndex ? pageIndex : 1 }, (response) => {
          this.musics = response.musics
          this.totalPage = response.totalPage
        })
      },

      // 推荐音乐
      getRecommendMusics() {
        this.post('/music/getRecommendMusics.do', {}, (resposne) => {
          this.recommendMusics = resposne.musics
        })
      },

      handleCurrentChange(value) {
        this.showAll ? this.getMusics(value) : this.search(value)
      },

      // 下载
      download(music) {
        location = '/download.do?songId=' + music.songId
      },

      // 显示管理弹窗
      manageSong(item) {
        if (item == null || item == undefined) {
          this.music = {}
          this.singer = {}
        } else {
          this.singerName = item.singerName
          this.music = item
          this.singer.singerId = this.music.singerId
          this.singer.singerName = this.music.singerName
          this.singer.singerSex = this.num2sex(this.music.singerSex)
          this.singer.singerNationality = this.music.singerNationality
          this.singer.singerRegion = this.music.singerRegion ? this.music.singerRegion : '未知'
        }
        this.inputDisable = true
        this.newMusic = false
        this.dialogTableVisible = true
      },

      // 修改音乐信息
      alterMusic() {
        this.dialogTableVisible = false
        console.warn(this.music)
        this.post('/music/updateSong.do',this.music, (response) => {
          this.$message.success('操作成功')
        })
      },

      // 添加歌曲
      insertMusic() {
        
      },

      // ----------------------------------------------- 歌手管理

      // 管理歌手信息
      manageSinger() {
        this.dialogTableVisible2 = true
        this.singer2 = JSON.parse(JSON.stringify(this.singer))
      },

      // 获取歌手信息
      getSingers(queryString, callback) {
        this.post('/music/getSingers.do', {}, (response) => {
          this.singers = response.singers
          var results = this.singers.map((singer) => {
            singer.value = singer.singerName
            return singer
          })
          results = queryString ? results.filter((item) => {
            return item.value.toLowerCase().indexOf(queryString.toLowerCase()) == 0
          }) : results
          callback(results)
        })
      },

      // 锁定歌手信息
      lockSingerInfo(singer) {
        this.singer = singer
        if (typeof(singer.singerSex) == 'number')
          this.singer.singerSex = singer.singerSex == 1 ? '男' : '女'
      },

      // 修改歌手信息
      alterSinger() {
        var singer = JSON.parse(JSON.stringify(this.singer2))
        singer.singerSex = singer.singerSex ? 1 : 0
        this.post('/music/updateSinger.do', singer, () => {
          this.dialogTableVisible2 = false
          // this.singer = JSON.parse(JSON.stringify(this.singer2))
          var musics = this.musics.filter((item) => {
            return item.singerName == this.singerName
          })
          if (musics.length != 0)
            musics[0].singerName = this.singer.singerName
          })
      },

      // ----------------------------------------------- 辅助功能

      // 功能键的功能实现，因为 el-dropdown-item 不能使用 @click
      dropdownCommand(command) {
        if (command == '退出登录')
          this.logout()
        else if (command == '侧边栏开关')
          this.asideShow = !this.asideShow
        else if (command == '添加歌曲') {
          this.newMusic = true
          this.inputDisable = false
          this.music = {}
          this.singer = {}
          this.dialogTableVisible = true
        }
      },

      // 从 cookie 中获取登陆的用户名
      getUsername() {
        var arr = document.cookie.replace(/[ ]/g, '').split(';')
        var str = ''
        for (var i = 0; i < arr.length; i++) 
          if(arr[i].startsWith("username="))
            str = arr[i].substring("username=".length)
        return str
      },

      // 数字2性别
      num2sex(i) {
        switch(i) {
          case 1 : return '男'
          case 0 : return '女'
          default : return '未知'
        }
      }
    },
    watch: {
      musicView: function() {
        if (!this.musicView)
          this.getRecommendMusics()
      }
    },
    mounted: function() {
      this.post('/user/loginTest.do', {}, () => {
        this.username = this.getUsername()
        if (!this.username) {
          location = "/"
        } else {
          this.getMusics()
          this.getFavoriteMusics()
        }
      }, () => {
        location = "/"
      })
    }
  };
</script>
