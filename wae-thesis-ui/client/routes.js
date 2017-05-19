import { App } from './app'
import {
  Home,
  HomeRoute,
  ExploreRoute,
  AboutRoute,
  NotFoundRoute,
  SignInRoute,
  SignUpRoute,
  FoodRoute,
  ElectronicRoute,
  HandmadeRoute
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
        SignInRoute,
        SignUpRoute,
        FoodRoute,
        ElectronicRoute,
        HandmadeRoute
      ]
    },
    {
      path: '/error',
      component: NotFoundRoute
    }
  ]
}
