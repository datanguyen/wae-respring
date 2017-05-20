import { combineReducers } from 'redux'
import { dashboard, food, electronic, handmade } from './pages'
import { cart } from './components'
import { authenticate } from './common'
export const AppReducer = combineReducers({
    dashboard, food, electronic, handmade, authenticate, cart
})
