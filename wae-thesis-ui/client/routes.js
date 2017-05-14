import {App} from './app'
import {
  Home,
  HomeRoute,
  ExploreRoute,
  AboutRoute,
  NotFoundRoute,
  SignInRoute
} from './pages'

export const AppRoute = {
  childRoutes: [
    {
      path: '/',
      component: App,
      indexRoute: {
        component: Home
      },
      childRoutes: [
        HomeRoute,
        ExploreRoute,
        AboutRoute,
        NotFoundRoute,
        SignInRoute
      ]
    }
  ]
}
