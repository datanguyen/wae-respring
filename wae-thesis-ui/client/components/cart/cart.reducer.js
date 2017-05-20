import shortid from 'shortid'
import { ADD_PRODUCT_CART, DELETE_PRODUCT_CART } from './cart.action.types'

let initialState = []

if (localStorage.waeCart) {
  initialState = JSON.parse(localStorage.waeCart)
}

export const cart = (state = initialState, action = {}) => {
  switch (action.type) {
    case ADD_PRODUCT_CART:
      let newAddedState = [
        ...state,
        {
          id: shortid.generate(),
          productName: action.product.productName,
          productPrice: action.product.productPrice,
          productUrl: action.product.productUrl
        }
      ]
      localStorage.waeCart = JSON.stringify(newAddedState)
      return newAddedState

    case DELETE_PRODUCT_CART:
      let index = state.findIndex(product => product.id === action.productId)
      let newDeletedState = index >= 0 ? [...state.slice(0, index), ...state.slice(index + 1)] : state
      localStorage.waeCart = JSON.stringify(newDeletedState)

      return newDeletedState
    default:
      return state
  }
}