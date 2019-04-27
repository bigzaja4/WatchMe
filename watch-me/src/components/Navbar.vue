<template>
  <div>
    <v-toolbar color="blue-grey" dark fixed app clipped-right>
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-toolbar-title>Toolbar</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
    <v-navigation-drawer v-model="drawer" fixed app>
      <v-list dense></v-list>
    </v-navigation-drawer>
    <v-navigation-drawer v-model="left" temporary fixed></v-navigation-drawer>
  </div>
</template>

<script>
export default {
  data: () => ({
    drawer: false,
    left: false
  })
};
</script>
<style scoped>
</style>
