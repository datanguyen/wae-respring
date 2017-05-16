import { combineReducers } from 'redux'
import { dashboard, food, electronic, handmade } from './pages'

export const AppReducer = combineReducers({
    dashboard, food, electronic, handmade
})
